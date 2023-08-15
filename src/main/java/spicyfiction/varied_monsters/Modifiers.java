package spicyfiction.varied_monsters;

public class Modifiers {

        String type;
        float val;

        public Modifiers(String type, float val) {
                this.type=type;
                this.val=val;
        }

        public Modifiers() {
                this.type= "default";
                this.val=0;
        }

        public float Modify(float oldValue) {
                switch(type) {
                        case "add" : return oldValue += val;
                        case "multiply" : return oldValue *= val;
                        case "divide" : return oldValue /= val;
                        case "set" : return oldValue = val;
                        case "default" : return oldValue;
                        default : return oldValue;
                }
        }




}
