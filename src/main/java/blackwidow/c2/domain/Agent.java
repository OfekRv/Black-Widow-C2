package blackwidow.c2.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Agent.
 */
@Entity
@Table(name = "agent")
public class Agent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ip", unique = true)
    private String ip;

    @Column(name = "last_active")
    private ZonedDateTime lastActive;

    @OneToMany(mappedBy = "agent")
    private Set<Command> commands = new HashSet<>();

    public Agent() {
    }

    public Agent(String ip, ZonedDateTime lastActive) {
        this.ip = ip;
        this.lastActive = lastActive;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public Agent ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ZonedDateTime getLastActive() {
        return lastActive;
    }

    public Agent lastActive(ZonedDateTime lastActive) {
        this.lastActive = lastActive;
        return this;
    }

    public void setLastActive(ZonedDateTime lastActive) {
        this.lastActive = lastActive;
    }

    public Set<Command> getCommands() {
        return commands;
    }

    public Agent commands(Set<Command> commands) {
        this.commands = commands;
        return this;
    }

    public Agent addCommands(Command command) {
        this.commands.add(command);
        command.setAgent(this);
        return this;
    }

    public Agent removeCommands(Command command) {
        this.commands.remove(command);
        command.setAgent(null);
        return this;
    }

    public void setCommands(Set<Command> commands) {
        this.commands = commands;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agent)) {
            return false;
        }
        return id != null && id.equals(((Agent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Agent{" +
            "id=" + getId() +
            ", ip='" + getIp() + "'" +
            ", lastActive='" + getLastActive() + "'" +
            "}";
    }
}
