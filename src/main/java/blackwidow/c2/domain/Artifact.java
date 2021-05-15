package blackwidow.c2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Artifact.
 */
@Entity
@Table(name = "artifact")
public class Artifact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "consume_time")
    private ZonedDateTime consumeTime;

    @Column(name = "sent_time")
    private ZonedDateTime sentTime;

    @ManyToOne
    @JsonIgnoreProperties(value = "artifacts", allowSetters = true)
    private Agent agent;

    public Artifact(ZonedDateTime time, String content, Agent sendingAgent) {
        this.agent = sendingAgent;
        this.consumeTime = ZonedDateTime.now();
        this.sentTime = time;
        this.content = content;
    }

    public Artifact() {
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public Artifact content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getConsumeTime() {
        return consumeTime;
    }

    public Artifact consumeTime(ZonedDateTime consumeTime) {
        this.consumeTime = consumeTime;
        return this;
    }

    public void setConsumeTime(ZonedDateTime consumeTime) {
        this.consumeTime = consumeTime;
    }

    public ZonedDateTime getSentTime() {
        return sentTime;
    }

    public Artifact sentTime(ZonedDateTime sentTime) {
        this.sentTime = sentTime;
        return this;
    }

    public void setSentTime(ZonedDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public Agent getAgent() {
        return agent;
    }

    public Artifact agent(Agent agent) {
        this.agent = agent;
        return this;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Artifact)) {
            return false;
        }
        return id != null && id.equals(((Artifact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Artifact{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", consumeTime='" + getConsumeTime() + "'" +
            ", sentTime='" + getSentTime() + "'" +
            "}";
    }
}
