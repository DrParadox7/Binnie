package binnie.extrabees.liquids;

import binnie.Binnie;
import binnie.core.liquid.FluidContainer;
import binnie.core.liquid.ILiquidType;
import binnie.core.util.I18N;
import binnie.extrabees.ExtraBees;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidStack;

import java.awt.Color;

public enum ExtraBeeLiquid implements ILiquidType {
	ACID("acid", new Color(11528985)), 
    	POISON("poison", new Color(15406315)), 
    	AMBROSIA("ambrosia", new Color(14392610)), 
    	ICHOR("ichor", new Color(16569932)), 
    	GLACIAL("liquidnitrogen", new Color(9881800));

	protected String ident;
	protected IIcon icon;
	protected int color;

	ExtraBeeLiquid(String ident, Color color) {
		this.ident = ident;
		this.color = color.getRGB();
	}

	@Override
	public IIcon getIcon() {
		return icon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcon(IIconRegister register) {
		icon = ExtraBees.proxy.getIcon(register, "liquids/" + getIdentifier());
	}

	@Override
	public String getName() {
		return I18N.localise("extrabees." + toString().toLowerCase());
	}

	@Override
	public String getIdentifier() {
		return ident;
	}

	@Override
	public int getColor() {
		return 0xffffff;
	}

	@Override
	public FluidStack get(int amount) {
		return Binnie.Liquid.getLiquidStack(ident, amount);
	}

	@Override
	public int getTransparency() {
		return 255;
	}

	@Override
	public boolean canPlaceIn(FluidContainer container) {
		return true;
	}

	@Override
	public boolean showInCreative(FluidContainer container) {
		return container == FluidContainer.Bucket
			|| container == FluidContainer.Can
			|| container == FluidContainer.Capsule
			|| container == FluidContainer.Refractory;
	}

	@Override
	public int getContainerColor() {
		return color;
	}
}
