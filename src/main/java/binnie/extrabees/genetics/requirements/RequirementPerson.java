package binnie.extrabees.genetics.requirements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.minecraft.util.EnumChatFormatting;

import binnie.core.util.I18N;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;

public class RequirementPerson implements IMutationRequirement {

    protected List<String> names;
    protected HashMap<String, Function<String, String>> nameFormatters = new HashMap<>();
    private static final Function<String, String> defaultApplier = name -> name;
    private static final String REQUIREMENT_USER_LOCALIZE_KEY = "extrabees.genetics.requirements.user";

    public RequirementPerson(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        if (names.isEmpty()) return I18N.localise(REQUIREMENT_USER_LOCALIZE_KEY, "No one");
        final StringBuilder namesFormatted = new StringBuilder("[");
        boolean hasNewLines = false;
        for (int i = 0, isize = names.size(); i < isize; i++) {
            if (i != 0) {
                namesFormatted.append(", ");
                if (i % 3 == 0) namesFormatted.append("/n ");
                hasNewLines = true;
            }
            String name = names.get(i);
            namesFormatted.append(nameFormatters.getOrDefault(name, defaultApplier).apply(name));
            namesFormatted.append(EnumChatFormatting.RESET);
        }
        namesFormatted.append("]");
        if (hasNewLines) namesFormatted.insert(0, "/n");
        return I18N.localise(REQUIREMENT_USER_LOCALIZE_KEY, namesFormatted.toString());
    }

    @Override
    public boolean fufilled(IBeeHousing housing, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1) {
        String ownerName = housing.getOwner().getName();
        return ownerName != null && ownerName.equals(name);
    }
}
