package cern.lsa.mapping.example.domain;

public interface BeamProcess extends SubContext {

    /**
     * Returns the name of the particle transfer that this beam process has been created for.
     *
     * @return the name of the particle transfer this beam process belongs to
     */
    String getParticleTransfer();

    /**
     * Returns the category of this beam process.
     *
     * @return the category of this beam process
     */
    String getCategory();

    /**
     * Returns the purpose of this beam process.
     *
     * @return the purpose of this beam process
     */
    String getPurpose();
}