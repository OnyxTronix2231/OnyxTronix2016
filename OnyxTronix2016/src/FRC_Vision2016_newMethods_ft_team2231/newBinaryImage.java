/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package FRC_Vision2016_newMethods_ft_team2231;

import java.rmi.server.RemoteObjectInvocationHandler;

import edu.wpi.first.wpilibj.image.MonoImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.util.SortedVector;

import com.ni.vision.NIVision;

/**
 * An image where each pixel is treated as either on or off.
 *
 * @author dtjones
 */
public class newBinaryImage extends newMonoImage {
  private int numParticles = -1;

  public newBinaryImage() throws NIVisionException {
	  super();
  }
  
  public newBinaryImage(newBinaryImage sourceImage) throws NIVisionException {         
	  super(sourceImage);
  }

  /**
   * Returns the number of particles.
   *
   * @return The number of particles
   */
  public int getNumberParticles() throws NIVisionException {
    if (numParticles < 0)
      numParticles = NIVision.imaqCountParticles(image, 1);
    return numParticles;
  }


  private class ParticleSizeReport {
    final int index;
    final double size;

    public ParticleSizeReport(int index) throws NIVisionException {
      if ((!(index < newBinaryImage.this.getNumberParticles())) || index < 0)
        throw new IndexOutOfBoundsException();
      this.index = index;
      size = newParticleAnalysisReport.getParticleToImagePercent(newBinaryImage.this, index);
    }

    public newParticleAnalysisReport getParticleAnalysisReport() throws NIVisionException {
      return new newParticleAnalysisReport(newBinaryImage.this, index);
    }
  }

  /**
   * Get a particle analysis report for the particle at the given index.
   *
   * @param index The index of the particle to report on.
   * @return The ParticleAnalysisReport for the particle at the given index
   */
  public newParticleAnalysisReport getParticleAnalysisReport(int index) throws NIVisionException {
    if (!(index < getNumberParticles()))
      throw new IndexOutOfBoundsException();
    return new newParticleAnalysisReport(this, index);
  }

  /**
   * Gets all the particle analysis reports ordered from largest area to
   * smallest.
   *
   * @param size The number of particles to return
   * @return An array of ParticleReports from largest area to smallest
   */
  public newParticleAnalysisReport[] getOrderedParticleAnalysisReports(int size)
      throws NIVisionException {
    if (size > getNumberParticles())
      size = getNumberParticles();
    ParticleSizeReport[] reports = new ParticleSizeReport[size];
    SortedVector sorter = new SortedVector(new SortedVector.Comparator() {
      public int compare(Object object1, Object object2) {
        ParticleSizeReport p1 = (ParticleSizeReport) object1;
        ParticleSizeReport p2 = (ParticleSizeReport) object2;
        if (p1.size < p2.size)
          return -1;
        else if (p1.size > p2.size)
          return 1;
        return 0;
      }
    });
    for (int i = 0; i < getNumberParticles(); i++)
      sorter.addElement(new ParticleSizeReport(i));
    sorter.setSize(size);
    sorter.copyInto(reports);
    newParticleAnalysisReport[] finalReports = new newParticleAnalysisReport[reports.length];
    for (int i = 0; i < finalReports.length; i++)
      finalReports[i] = reports[i].getParticleAnalysisReport();
    return finalReports;
  }

  /**
   * Gets all the particle analysis reports ordered from largest area to
   * smallest.
   *
   * @return An array of ParticleReports from largest are to smallest
   */
  public newParticleAnalysisReport[] getOrderedParticleAnalysisReports() throws NIVisionException {
    return getOrderedParticleAnalysisReports(getNumberParticles());
  }


  public void write(String fileName) throws NIVisionException {
    NIVision.RGBValue colorTable = new NIVision.RGBValue(0, 0, 255, 0);
    try {
      NIVision.imaqWriteFile(image, fileName, colorTable);
    } finally {
      colorTable.free();
    }
  }

  /**
   * removeSmallObjects filters particles based on their size. The algorithm
   * erodes the image a specified number of times and keeps the particles from
   * the original image that remain in the eroded image.
   *
   * @param connectivity8 true to use connectivity-8 or false for connectivity-4
   *        to determine whether particles are touching. For more information
   *        about connectivity, see Chapter 9, Binary Morphology, in the NI
   *        Vision Concepts manual.
   * @param erosions the number of erosions to perform
   * @return a BinaryImage after applying the filter
   */
  public newBinaryImage removeSmallObjects(boolean connectivity8, int erosions)
      throws NIVisionException {
    newBinaryImage result = new newBinaryImage();
    NIVision.imaqSizeFilter(result.image, image, connectivity8 ? 1 : 0, erosions,
        NIVision.SizeType.KEEP_LARGE, null);
    result.free();
    return result;
  }

  /**
   * removeLargeObjects filters particles based on their size. The algorithm
   * erodes the image a specified number of times and discards the particles
   * from the original image that remain in the eroded image.
   *
   * @param connectivity8 true to use connectivity-8 or false for connectivity-4
   *        to determine whether particles are touching. For more information
   *        about connectivity, see Chapter 9, Binary Morphology, in the NI
   *        Vision Concepts manual.
   * @param erosions the number of erosions to perform
   * @return a BinaryImage after applying the filter
   */
  public newBinaryImage removeLargeObjects(boolean connectivity8, int erosions)
      throws NIVisionException {
    newBinaryImage result = new newBinaryImage();
    NIVision.imaqSizeFilter(result.image, image, connectivity8 ? 1 : 0, erosions,
        NIVision.SizeType.KEEP_SMALL, null);
    return result;
  }

  public newBinaryImage convexHull(boolean connectivity8) throws NIVisionException {
    newBinaryImage result = new newBinaryImage();
    NIVision.imaqConvexHull(result.image, image, connectivity8 ? 1 : 0);
    return result;
  }

  public newBinaryImage particleFilter(NIVision.ParticleFilterCriteria2[] criteria)
      throws NIVisionException {
    newBinaryImage result = new newBinaryImage();
    NIVision.ParticleFilterOptions2 options = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
    NIVision.imaqParticleFilter4(result.image, image, criteria, options,NIVision.imaqCreateROI() );
    options.free();
    return result;
  }
}
