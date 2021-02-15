package blackwidow.c2.service.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class IncomingArtifactDto {
    private String content;
    private String agentIp;

    public String getContent() {
        return content;
    }

    public String getAgentIp() {
        return agentIp;
    }
}
