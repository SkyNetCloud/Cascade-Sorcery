package net.mcreator.voltage.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.voltage.VoltageModVariables;
import net.mcreator.voltage.VoltageModElements;

import java.util.Map;

@VoltageModElements.ModElement.Tag
public class BatteryBlockDestroyedByPlayerProcedure extends VoltageModElements.ModElement {
	public BatteryBlockDestroyedByPlayerProcedure(VoltageModElements instance) {
		super(instance, 4);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BatteryBlockDestroyedByPlayer!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		VoltageModVariables.MapVariables.get(world).Tmax = (double) ((VoltageModVariables.MapVariables.get(world).Tmax) - 2500000);
		VoltageModVariables.MapVariables.get(world).syncData(world);
	}
}
