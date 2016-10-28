package binnie.craftgui.extratrees.kitchen;

import binnie.core.AbstractMod;
import binnie.core.machines.Machine;
import binnie.craftgui.minecraft.Window;
import binnie.craftgui.minecraft.control.ControlPlayerInventory;
import binnie.extratrees.ExtraTrees;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;

public class WindowBottleRack extends Window {
	public WindowBottleRack(final EntityPlayer player, final IInventory inventory, final Side side) {
		super(248.0f, 180.0f, player, inventory, side);
	}

	public static Window create(final EntityPlayer player, final IInventory inventory, final Side side) {
		return new WindowBottleRack(player, inventory, side);
	}

	@Override
	protected AbstractMod getMod() {
		return ExtraTrees.instance;
	}

	@Override
	protected String getBackgroundTextureName() {
		return "BottleBank";
	}

	@Override
	public void initialiseClient() {
		this.setTitle(Machine.getMachine(this.getInventory()).getPackage().getDisplayName());
		for (int i = 0; i < 36; ++i) {
			final int x = i % 12;
			final int y = i / 12;
			new ControlTankSlot(this, 16 + x * 18, 32 + y * 18, i);
		}
		new ControlPlayerInventory(this);
	}
}
