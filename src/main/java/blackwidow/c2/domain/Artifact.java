package blackwidow.c2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Artifact.
 */
@Entity
@Table(name = "artifact")

@AllArgsConstructor
@NoArgsConstructor
public class Artifact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "receive_time")
    private ZonedDateTime receiveTime;

    @Column(name = "content")
    private String content;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "artifacts", allowSetters = true)
    private Agent agent;

    public Artifact() {
    }

    public Artifact(ZonedDateTime receiveTime, String content, Agent agent) {
        this.receiveTime = receiveTime;
        this.content = content;
        this.agent = agent;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getReceiveTime() {
        return receiveTime;
    }

    public Artifact receiveTime(ZonedDateTime receiveTime) {
        this.receiveTime = receiveTime;
        return this;
    }

    public void setReceiveTime(ZonedDateTime receiveTime) {
        this.receiveTime = receiveTime;
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
            ", receiveTime='" + getReceiveTime() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
