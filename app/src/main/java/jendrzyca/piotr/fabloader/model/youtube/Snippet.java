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
        "publishedAt",
        "channelId",
        "title",
        "description",
        "thumbnails",
        "channelTitle",
        "liveBroadcastContent"
})
public class Snippet {

    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("thumbnails")
    private Thumbnails thumbnails;
    @JsonProperty("channelTitle")
    private String channelTitle;
    @JsonProperty("liveBroadcastContent")
    private String liveBroadcastContent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The publishedAt
     */
    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * @param publishedAt The publishedAt
     */
    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * @return The channelId
     */
    @JsonProperty("channelId")
    public String getChannelId() {
        return channelId;
    }

    /**
     * @param channelId The channelId
     */
    @JsonProperty("channelId")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * @return The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The thumbnails
     */
    @JsonProperty("thumbnails")
    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    /**
     * @param thumbnails The thumbnails
     */
    @JsonProperty("thumbnails")
    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    /**
     * @return The channelTitle
     */
    @JsonProperty("channelTitle")
    public String getChannelTitle() {
        return channelTitle;
    }

    /**
     * @param channelTitle The channelTitle
     */
    @JsonProperty("channelTitle")
    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    /**
     * @return The liveBroadcastContent
     */
    @JsonProperty("liveBroadcastContent")
    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    /**
     * @param liveBroadcastContent The liveBroadcastContent
     */
    @JsonProperty("liveBroadcastContent")
    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
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
        return new HashCodeBuilder().append(publishedAt).append(channelId).append(title).append(description).append(thumbnails).append(channelTitle).append(liveBroadcastContent).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Snippet) == false) {
            return false;
        }
        Snippet rhs = ((Snippet) other);
        return new EqualsBuilder().append(publishedAt, rhs.publishedAt).append(channelId, rhs.channelId).append(title, rhs.title).append(description, rhs.description).append(thumbnails, rhs.thumbnails).append(channelTitle, rhs.channelTitle).append(liveBroadcastContent, rhs.liveBroadcastContent).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
