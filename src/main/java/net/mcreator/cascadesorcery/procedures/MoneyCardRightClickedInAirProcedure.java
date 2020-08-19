package net.mcreator.cascadesorcery.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.cascadesorcery.CascadeSorceryModElements;

import java.util.Map;
import java.util.HashMap;

@CascadeSorceryModElements.ModElement.Tag
public class MoneyCardRightClickedInAirProcedure extends CascadeSorceryModElements.ModElement {
	public MoneyCardRightClickedInAirProcedure(CascadeSorceryModElements instance) {
		super(instance, 27);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("guistate") == null) {
			System.err.println("Failed to load dependency guistate for procedure MoneyCardRightClickedInAir!");
			return;
		}
		HashMap guistate = (HashMap) dependencies.get("guistate");
		double Money_int = 0;
		if ((((Money_int) > 0) && ((Money_int) == 0))) {
			{
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().sendMessage(new StringTextComponent((new Object() {
						public String getText() {
							TextFieldWidget textField = (TextFieldWidget) guistate.get("text:skynetcloud");
							if (textField != null) {
								return textField.getText();
							}
							return "";
						}
					}.getText())));
			}
		}
	}
}
