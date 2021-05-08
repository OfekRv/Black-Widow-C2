package blackwidow.c2.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ArchivedAsyncArtifact.
 */
@Entity
@Table(name = "archived_async_artifact")
public class ArchivedAsyncArtifact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "consume_time")
    private ZonedDateTime consumeTime;

    public ArchivedAsyncArtifact(String messageId, ZonedDateTime consumeTime) {
        this.messageId = messageId;
        this.consumeTime = consumeTime;
    }

    public ArchivedAsyncArtifact() { }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public ArchivedAsyncArtifact messageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public ZonedDateTime getConsumeTime() {
        return consumeTime;
    }

    public ArchivedAsyncArtifact consumeTime(ZonedDateTime consumeTime) {
        this.consumeTime = consumeTime;
        return this;
    }

    public void setConsumeTime(ZonedDateTime consumeTime) {
        this.consumeTime = consumeTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArchivedAsyncArtifact)) {
            return false;
        }
        return id != null && id.equals(((ArchivedAsyncArtifact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArchivedAsyncArtifact{" +
            "id=" + getId() +
            ", messageId='" + getMessageId() + "'" +
            ", consumeTime='" + getConsumeTime() + "'" +
            "}";
    }
}
