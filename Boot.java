public class Boot extends LakeObject {
    public Boot() {
        super();
        setCost(0); // Boots are free
    }

    @Override
    public String say() {
        return "You pulled out a boot... again.";
    }

    @Override
    public boolean wasCaught(Hook h) {
        return true; // Always caught regardless of hook strength
    }
}