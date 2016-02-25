package org.usfirst.frc2231.OnyxTronix2016;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_ChevalDeFrise;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Gate;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_LowBar;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Moat;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Ramparts;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Rockwall;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_RoughTerrain;

import FRC_Vision2016_newMethods_ft_team2231.newParticleAnalysisReport;
import edu.wpi.first.wpilibj.command.Command;

public class Obstacles {
	private static final int[] LOWBAR_POSITIONS = {1};
	private static final int[] CATEGORY_A_POSITIONS = {3};
	private static final int[] DEFAULT_POSITIONS = {2, 4, 5};
	public enum Obstacle{
		Lowbar(new Autonomous_LowBar(), LOWBAR_POSITIONS),
		Gate(new Autonomous_Gate(), CATEGORY_A_POSITIONS),
		ChevalDeFrise(new Autonomous_ChevalDeFrise(), CATEGORY_A_POSITIONS),
		Moat(new Autonomous_Moat(), DEFAULT_POSITIONS),
		Ramparts(new Autonomous_Ramparts(), DEFAULT_POSITIONS),
		Rockwall(new Autonomous_Rockwall(), DEFAULT_POSITIONS),
		RoughTerrain(new Autonomous_RoughTerrain(), DEFAULT_POSITIONS);
		
		
		private Command commnad;
		private int[] positions;
		
		Obstacle(Command autoCommand, int[] possiblePositions){
			commnad = autoCommand;
			positions = possiblePositions;
		}
		
		public Command getCommand(){
			return this.commnad;
		}
		
		public int[] getPositions() {
			return this.positions;
		}
	}
}
