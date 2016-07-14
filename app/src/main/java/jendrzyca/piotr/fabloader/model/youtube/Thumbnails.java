package jendrzyca.piotr.fabloader.model.youtube;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "default",
        "medium",
        "high"
})
public class Thumbnails {

    @JsonProperty("default")
    private Default _default;
    @JsonProperty("medium")
    private Medium medium;
    @JsonProperty("high")
    private High high;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The _default
     */
    @JsonProperty("default")
    public Default getDefault() {
        return _default;
    }

    /**
     * @param _default The default
     */
    @JsonProperty("default")
    public void setDefault(Default _default) {
        this._default = _default;
    }

    /**
     * @return The medium
     */
    @JsonProperty("medium")
    public Medium getMedium() {
        return medium;
    }

    /**
     * @param medium The medium
     */
    @JsonProperty("medium")
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    /**
     * @return The high
     */
    @JsonProperty("high")
    public High getHigh() {
        return high;
    }

    /**
     * @param high The high
     */
    @JsonProperty("high")
    public void setHigh(High high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(_default).append(medium).append(high).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Thumbnails) == false) {
            return false;
        }
        Thumbnails rhs = ((Thumbnails) other);
        return new EqualsBuilder().append(_default, rhs._default).append(medium, rhs.medium).append(high, rhs.high).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
