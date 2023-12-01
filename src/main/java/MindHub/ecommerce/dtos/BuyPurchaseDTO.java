package MindHub.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

public class BuyPurchaseDTO {

    private Set<CreamBuyDTO> creamBuyDTOSet;

    private Set<FlavoringBuyDTO> flavoringBuyDTOSet;

    private Set <FraganceBuyDTO> fraganceBuyDTOSet;



    public Set<CreamBuyDTO> getCreamBuyDTOSet() {
        return creamBuyDTOSet;
    }

    public Set<FlavoringBuyDTO> getFlavoringBuyDTOSet() {
        return flavoringBuyDTOSet;
    }

    public Set<FraganceBuyDTO> getFraganceBuyDTOSet() {
        return fraganceBuyDTOSet;
    }
}
