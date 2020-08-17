package net.mcreator.voltage.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.voltage.VoltageModVariables;
import net.mcreator.voltage.VoltageModElements;

import java.util.Map;

@VoltageModElements.ModElement.Tag
public class Tenter1Procedure extends VoltageModElements.ModElement {
	public Tenter1Procedure(VoltageModElements instance) {
		super(instance, 6);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Tenter1!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		VoltageModVariables.MapVariables.get(world).Tenter = (double) 1;
		VoltageModVariables.MapVariables.get(world).syncData(world);
	}
}
