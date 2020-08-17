package net.mcreator.voltage.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.voltage.VoltageModVariables;
import net.mcreator.voltage.VoltageModElements;

import java.util.Map;

@VoltageModElements.ModElement.Tag
public class GeneratorUpdateTickProcedure extends VoltageModElements.ModElement {
	public GeneratorUpdateTickProcedure(VoltageModElements instance) {
		super(instance, 1);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure GeneratorUpdateTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getWorld().isDaytime())
				&& ((VoltageModVariables.MapVariables.get(world).Tenergy) < (VoltageModVariables.MapVariables.get(world).Tmax)))
				&& ((VoltageModVariables.MapVariables.get(world).Tenter) == 1))) {
			VoltageModVariables.MapVariables
					.get(world).Tenergy = (double) ((VoltageModVariables.MapVariables.get(world).Tenergy) + Math.max(20000, 100000));
			VoltageModVariables.MapVariables.get(world).syncData(world);
			if (((VoltageModVariables.MapVariables.get(world).Tenergy) > (VoltageModVariables.MapVariables.get(world).Tmax))) {
				VoltageModVariables.MapVariables.get(world).Tenergy = (double) (VoltageModVariables.MapVariables.get(world).Tmax);
				VoltageModVariables.MapVariables.get(world).syncData(world);
			}
		}
	}
}
