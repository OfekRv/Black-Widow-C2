package blackwidow.c2.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the {@link blackwidow.c2.domain.Artifact} entity. This class is used
 * in {@link blackwidow.c2.web.rest.ArtifactResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /artifacts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ArtifactCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter receiveTime;

    private StringFilter content;

    private LongFilter agentId;

    public ArtifactCriteria() {
    }

    public ArtifactCriteria(ArtifactCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.receiveTime = other.receiveTime == null ? null : other.receiveTime.copy();
        this.content = other.content == null ? null : other.content.copy();
        this.agentId = other.agentId == null ? null : other.agentId.copy();
        this.agentId = other.agentId == null ? null : other.agentId.copy();
    }

    @Override
    public ArtifactCriteria copy() {
        return new ArtifactCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public ZonedDateTimeFilter getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(ZonedDateTimeFilter receiveTime) {
        this.receiveTime = receiveTime;
    }

    public StringFilter getContent() {
        return content;
    }

    public void setContent(StringFilter content) {
        this.content = content;
    }

    public LongFilter getAgentId() {
        return agentId;
    }

    public void setAgentId(LongFilter agentId) {
        this.agentId = agentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ArtifactCriteria that = (ArtifactCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(receiveTime, that.receiveTime) &&
            Objects.equals(content, that.content) &&
            Objects.equals(agentId, that.agentId) &&
            Objects.equals(agentId, that.agentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        receiveTime,
        content,
        agentId,
        agentId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArtifactCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (receiveTime != null ? "receiveTime=" + receiveTime + ", " : "") +
                (content != null ? "content=" + content + ", " : "") +
                (agentId != null ? "agentId=" + agentId + ", " : "") +
                (agentId != null ? "agentId=" + agentId + ", " : "") +
            "}";
    }

}
