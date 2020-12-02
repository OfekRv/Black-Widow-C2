package blackwidow.c2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import blackwidow.c2.domain.enumeration.CommandStatus;

import blackwidow.c2.domain.enumeration.CommandType;

/**
 * A Command.
 */
@Entity
@Table(name = "command")
public class Command implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "send_time")
    private ZonedDateTime sendTime = ZonedDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CommandStatus status = CommandStatus.NEW;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CommandType type;

    @ManyToOne
    @JsonIgnoreProperties(value = "commands", allowSetters = true)
    private Agent agent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getSendTime() {
        return sendTime;
    }

    public Command sendTime(ZonedDateTime sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    public void setSendTime(ZonedDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public CommandStatus getStatus() {
        return status;
    }

    public Command status(CommandStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CommandStatus status) {
        this.status = status;
    }

    public CommandType getType() {
        return type;
    }

    public Command type(CommandType type) {
        this.type = type;
        return this;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public Agent getAgent() {
        return agent;
    }

    public Command agent(Agent agent) {
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
        if (!(o instanceof Command)) {
            return false;
        }
        return id != null && id.equals(((Command) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Command{" +
            "id=" + getId() +
            ", sendTime='" + getSendTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
