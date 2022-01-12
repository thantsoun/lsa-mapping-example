package cern.lsa.mapping.example.domain;

public class BeamProcessImpl extends SubContextImpl implements BeamProcess {

    private String category;
    private String purpose;
    private String particleTransfer;

    public BeamProcessImpl(long id, String name) {
        super(id, name);
    }

    @Override
    public String getParticleTransfer() {
        return this.particleTransfer;
    }

    public void setParticleTransfer(String particleTransfer) {
        this.particleTransfer = particleTransfer;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

}