package net.mcreator.voltage.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.voltage.VoltageModVariables;
import net.mcreator.voltage.VoltageModElements;

import java.util.Map;

@VoltageModElements.ModElement.Tag
public class Tenter0Procedure extends VoltageModElements.ModElement {
	public Tenter0Procedure(VoltageModElements instance) {
		super(instance, 5);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Tenter0!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		VoltageModVariables.MapVariables.get(world).Tenter = (double) 0;
		VoltageModVariables.MapVariables.get(world).syncData(world);
	}
}
