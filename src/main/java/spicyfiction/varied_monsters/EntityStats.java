package spicyfiction.varied_monsters;

public class EntityStats {
        public Modifiers health;
        public Modifiers attackStrength;
        public Modifiers moveSpeed;

        public EntityStats(Modifiers healthModifier, Modifiers attackModifier, Modifiers speedModifier) {
            this.health = healthModifier;
            this.attackStrength = attackModifier;
            this.moveSpeed = speedModifier;
        }



}
