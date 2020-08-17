package net.mcreator.voltage.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Explosion;

import net.mcreator.voltage.VoltageModVariables;
import net.mcreator.voltage.VoltageModElements;

import java.util.Map;

@VoltageModElements.ModElement.Tag
public class StorageBlockDestroyedByExplosionProcedure extends VoltageModElements.ModElement {
	public StorageBlockDestroyedByExplosionProcedure(VoltageModElements instance) {
		super(instance, 11);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure StorageBlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure StorageBlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure StorageBlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure StorageBlockDestroyedByExplosion!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof World && !world.getWorld().isRemote) {
			world.getWorld().createExplosion(null, (int) x, (int) y, (int) z, (float) 4, Explosion.Mode.BREAK);
		}
		VoltageModVariables.MapVariables.get(world).Tmax = (double) ((VoltageModVariables.MapVariables.get(world).Tmax) - 2500000);
		VoltageModVariables.MapVariables.get(world).syncData(world);
	}
}
