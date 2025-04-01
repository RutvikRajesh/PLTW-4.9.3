public class Bait extends LakeObject {
    public Bait() {
        super();
        setCost(10); // Fixed cost as per instructions
    }

    @Override
    public String say() {
        return "You now have bait!";
    }
}