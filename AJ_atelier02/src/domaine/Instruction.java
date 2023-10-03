package domaine;

import java.time.Duration;

public class Instruction {
    private String description;
    private Duration dureeEnMinutes;
    public Instruction(String description, int dureeEnMinutes){
        setDescription(description);
        this.dureeEnMinutes=Duration.ofMinutes(dureeEnMinutes);
    }

    public String getDescription() {
        return description;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDureeEnMinutes(Duration dureeEnMinutes) {
        this.dureeEnMinutes = dureeEnMinutes;
    }
    public String toString(){
        return "("+String.format("%02d:%02d", (dureeEnMinutes.toMinutes() % 3600) / 60, (dureeEnMinutes.toMinutes()) % 60)+")"+description;
    }
}
