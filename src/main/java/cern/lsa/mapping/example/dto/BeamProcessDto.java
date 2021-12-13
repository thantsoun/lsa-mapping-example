package cern.lsa.mapping.example.dto;

public class BeamProcessDto extends SubContextDto {

    private String category;
    private String purpose;
    private String particleTransfer;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getParticleTransfer() {
        return particleTransfer;
    }

    public void setParticleTransfer(String particleTransfer) {
        this.particleTransfer = particleTransfer;
    }

}