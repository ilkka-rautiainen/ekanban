package fi.aalto.ekanban.models;

import java.util.Map;

public class AdjustWipLimitsAction {

    // Phase id -> new wip-limit
    private Map<String, Integer> phaseWipLimits;

    public Map<String, Integer> getPhaseWipLimits() {
        return phaseWipLimits;
    }

    public void setPhaseWipLimits(Map<String, Integer> phaseWipLimits) {
        this.phaseWipLimits = phaseWipLimits;
    }

}
