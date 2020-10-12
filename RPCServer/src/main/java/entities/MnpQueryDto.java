package entities;

public class MnpQueryDto {
    private long mnpId;
    private String providerId;
    private String ogiProviderId;
    private long startTime;
    private int status;
    private String destination;
    private String providerName;

    public long getMnpId() {
        return mnpId;
    }

    public void setMnpId(long mnpId) {
        this.mnpId = mnpId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getOgiProviderId() {
        return ogiProviderId;
    }

    public void setOgiProviderId(String ogiProviderId) {
        this.ogiProviderId = ogiProviderId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
