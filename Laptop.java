public class Laptop {
    String company;
    String gpu;
    int ram_gb;
    String cpu;

    @Override
    public String toString() {
        return "company: " + company + " gpu: " + gpu + " ram_gb: "+ ram_gb + " cpu: " + cpu;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
        {
            return true;
        }
        if(!(obj instanceof Laptop))
        {
            return false;
        }
        Laptop lap = (Laptop) obj;
        return company.equals(lap.company) && gpu.equals(lap.gpu) && ram_gb == lap.ram_gb && cpu.equals(lap.cpu);
    }

    @Override
    public int hashCode() {
        return company.hashCode() + 7 * ram_gb + 13 * cpu.hashCode() + gpu.hashCode();
    }
}
