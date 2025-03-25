
    public class Item {
        private String name;
        private int importante;
        private Location location;


        public Item(String name,int importante, Location location) {
            this.importante = importante;
            this.name = name;
            this.location = location;
        }
        public int importanteId() {
            return importante;
        }


        public String getName() {
            return name;
        }

        public Location getLocation() {
            return location;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

