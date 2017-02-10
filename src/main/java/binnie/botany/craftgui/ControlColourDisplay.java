package binnie.botany.craftgui;

import binnie.botany.api.IFlowerColour;
import binnie.craftgui.controls.core.Control;
import binnie.craftgui.controls.core.IControlValue;
import binnie.craftgui.core.Attribute;
import binnie.craftgui.core.ITooltip;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.Tooltip;
import binnie.craftgui.core.renderer.RenderUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ControlColourDisplay extends Control implements IControlValue<IFlowerColour>, ITooltip {
	private IFlowerColour value;

	public ControlColourDisplay(final IWidget parent, final int x, final int y, final IFlowerColour value) {
		super(parent, x, y, 16, 16);
		this.value = value;
		this.addAttribute(Attribute.MouseOver);
	}

	@Override
	public IFlowerColour getValue() {
		return this.value;
	}

	@Override
	public void setValue(final IFlowerColour value) {
		this.value = value;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onRenderBackground(int guiWidth, int guiHeight) {
		RenderUtil.drawSolidRect(this.getArea(), -1);
		RenderUtil.drawSolidRect(this.getArea().inset(1), -16777216 + this.getValue().getColor(false));
	}

	@Override
	public void getTooltip(final Tooltip tooltip) {
		super.getTooltip(tooltip);
		tooltip.add(this.value.getColourName());
	}
}