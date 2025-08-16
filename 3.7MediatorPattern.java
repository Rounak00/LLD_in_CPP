//Objects talk via mediator

import java.util.*;

// Step 1: Mediator Interface
interface AuctionMediator {
    void addBidder(Bidder bidder);
    void placeBid(Bidder bidder, int bidAmount);
}

// Step 2: Concrete Mediator
class Auction implements AuctionMediator {
    private List<Bidder> bidders = new ArrayList<>();

    @Override
    public void addBidder(Bidder bidder) {
        bidders.add(bidder);
    }

    @Override
    public void placeBid(Bidder bidder, int bidAmount) {
        System.out.println(bidder.getName() + " placed bid of " + bidAmount + " Rs.");

        // notify all other bidders
        for (Bidder b : bidders) {
            if (!b.equals(bidder)) {
                b.receiveBidNotification(bidder.getName(), bidAmount);
            }
        }
    }
}

// Step 3: Colleague Interface
interface Colleague {
    void placeBid(int bidAmount);
    void receiveBidNotification(String bidderName, int bidAmount);
    String getName();
}

// Step 4: Concrete Colleague
class Bidder implements Colleague {
    private String name;
    private AuctionMediator mediator;

    public Bidder(String name, AuctionMediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.addBidder(this); // register with mediator
    }

    @Override
    public void placeBid(int bidAmount) {
        mediator.placeBid(this, bidAmount);
    }

    @Override
    public void receiveBidNotification(String bidderName, int bidAmount) {
        System.out.println("Bidder " + name + " got notification: " +
                bidderName + " placed bid of " + bidAmount + " Rs.");
    }

    @Override
    public String getName() {
        return name;
    }
}

// Step 5: Client
public class MediatorPatternDemo {
    public static void main(String[] args) {
        AuctionMediator auction = new Auction();

        Bidder bidder1 = new Bidder("Alice", auction);
        Bidder bidder2 = new Bidder("Bob", auction);
        Bidder bidder3 = new Bidder("Charlie", auction);

        bidder1.placeBid(1000);
        bidder2.placeBid(1500);
        bidder3.placeBid(2000);
    }
}

//Outputs
/*
Alice placed bid of 1000 Rs.
Bidder Bob got notification: Alice placed bid of 1000 Rs.
Bidder Charlie got notification: Alice placed bid of 1000 Rs.

Bob placed bid of 1500 Rs.
Bidder Alice got notification: Bob placed bid of 1500 Rs.
Bidder Charlie got notification: Bob placed bid of 1500 Rs.

Charlie placed bid of 2000 Rs.
Bidder Alice got notification: Charlie placed bid of 2000 Rs.
Bidder Bob got notification: Charlie placed bid of 2000 Rs.
*/