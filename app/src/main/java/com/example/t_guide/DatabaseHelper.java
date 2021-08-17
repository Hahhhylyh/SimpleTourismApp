package com.example.t_guide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME ="TGuide.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Culture(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, t1 TEXT, t2 TEXT, t3 TEXT, t4 TEXT, t5 TEXT, t6 TEXT, t7 TEXT, t8 TEXT, t9 TEXT, t10 TEXT, link TEXT)");
        db.execSQL("CREATE TABLE Location(id INTEGER PRIMARY KEY AUTOINCREMENT, country TEXT, location TEXT, title TEXT, summary TEXT, ts1 TEXT, ts2 TEXT, ts3 TEXT, ts4 TEXT, t1 TEXT, t2 TEXT)");
        db.execSQL("CREATE TABLE User(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, name TEXT, password TEXT, registeredDate DATE DEFAULT CURRENT_DATE, img BLOB DEFAULT NULL)");
        db.execSQL("CREATE TABLE Review(id INTEGER PRIMARY KEY AUTOINCREMENT, rating INT, review TEXT, latestDate DATETIME DEFAULT CURRENT_TIMESTAMP, country TEXT, location TEXT, user INT)");

        fillCultureTable(db);
        fillLocationTable(db);
        fillUserTable(db);
        fillReviewTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Culture");
        db.execSQL("DROP TABLE IF EXISTS Location");
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Review");
        onCreate(db);
    }

    private void fillCultureTable(SQLiteDatabase db){
        addCulture(db, "Top 10 Cultural do’s and Don’t’s for Malaysia.", "1. Do wear appropriate clothing!", "2. Do ask Before, taking photos of locals. ", "3. Do the ” Salam” rather than a normal handshake!", "4. Do take your hat off!", "5. Don’t touch anyones Head!", "6. Don’t show the souls of your feet.", "7. Don’t show anger or frustration.", "8. Don’t insult while bartering!", "9. Don’t give to street children – Honestly i’m not heartless!", "10. Don’t handle objects with your left hand only!", "https://www.townandtourist.com/malaysia-culture-guide/");
        addCulture(db, "10 Do's and Don'ts for tourists in Singapore", "1. Do dress for the weather", "2. Don't litter", "3. Do stand on the correct side", "4. Don't use public transport during peak times", "5. Don't jaywalk on roads", "6. Do join the queue", "7. Do mind your manners", "8. Don't even think about tipping", "9. Do carry cash", "10. Do treat the elders like family", "https://www.timeout.com/singapore/things-to-do/dos-and-donts-for-tourists-in-singapore");
        addCulture(db, "10 Cultural Dos and Don'ts in Japan", "1. Do take off your shoes", "2. Don't share", "3. Do follows the rules", "4. Don't touch anyone", "5. Do slurp", "6. Don't play with your chopsticks", "7. Do tone it down", "8. Don't tip your waiter", "9. Do keep to the left", "10. Don't stress out about the cultural dos and don'ts in Japan", "https://www.tokyocreative.com/articles/18347-10-cultural-dos-and-donts-in-japan");
        addCulture(db, "10 Dos and Don'ts for Tourists in Hong Kong", "1. DO have dim sum", "2. DON’T spend the entire time eating and shopping", "3. DO pack a cardigan", "4. DON’T be a slowpoke", "5. DO research your restaurants", "6. DON’T smoke in restaurants, bars, public parks or beaches", "7. DO take the tram", "8. DON’T get the tourist tram pass", "9. DO get an Octopus Card", "10. DON’T take the MTR during rush hour", "https://theculturetrip.com/asia/china/hong-kong/articles/10-dos-and-donts-for-tourists-in-hong-kong/");
        addCulture(db, "10 Simple Do's and Don'ts in Bali to NOT Look Like a Tourist", "1. Do dress appropriately", "2. Do make sure you barter with compassion", "3. Don't bother heading to Kuta", "4. Don't rent a scooter if you have never driven one", "5. Don't contribute to the litter problem", "6. Do explore off the beaten path", "7. Do ensure you learn some basic Balinese", "8. Do try the local Arak", "9. Do not expect Western standards", "10. Do make sure to look both sides when crossing the street", "https://www.theinvisibletourist.com/dos-and-donts-in-bali-travel-tips-indonesia/");
        addCulture(db, "Thailand Do’s and Don’ts", "1. Do respect all Buddha images", "2. Do dress properly when visiting a temple", "3. Do remove your shoes before entering a temple", "4. Do eat with a spoon", "5. Don’t show disrespect towards the Thai Royal Family", "6. Don’t touch a Thai woman", "7. Don’t be overly affectionate in public", "8. Don’t sunbathe nude", "9. Don’t touch a Thai person’s head or ruffle their hair", "10. Don’t place your feet on the table", "https://www.thaizer.com/thailand-dos-and-donts/");
    }

    private void addCulture(SQLiteDatabase db, String t, String t1, String t2, String t3, String t4, String t5, String t6, String t7, String t8, String t9, String t10, String l){
        ContentValues cv = new ContentValues();
        cv.put("title", t);
        cv.put("t1", t1);
        cv.put("t2", t2);
        cv.put("t3", t3);
        cv.put("t4", t4);
        cv.put("t5", t5);
        cv.put("t6", t6);
        cv.put("t7", t7);
        cv.put("t8", t8);
        cv.put("t9", t9);
        cv.put("t10", t10);
        cv.put("link", l);
        db.insert("Culture", null, cv);
    }

    private void fillLocationTable(SQLiteDatabase db){
        addLocation(db, "1", "1", "Petronas Twin Towers", "The Petronas Towers, also known as the Petronas Twin Towers, are twin skyscrapers in Kuala Lumpur, Malaysia. According to the Council on Tall Buildings and Urban Habitat's official definition and ranking, they were the tallest buildings in the world from 1998 to 2004 when they were surpassed by Taipei 101. The Petronas Towers remain the tallest twin towers in the world. The buildings are a landmark of Kuala Lumpur, along with nearby Kuala Lumpur Tower; they remain the tallest buildings in Kuala Lumpur.", "High End Shopping Mall", "Night View", "Fountain Show with Lights and Music", "Skybridge", "The best time for visiting the Petronas Towers is in the evening. You can time it so that you can see the KLCC light show which will save you from travelling back there.", "Get there at least 15 minutes before your time slot. If you are late and miss it, you will not be allowed up and will not be eligible for a refund or to rebook.");
        addLocation(db, "1", "2","Resorts World Genting", "Genting Highlands is a hill station located on the peak of Mount Ulu Kali in Malaysia at 1,800 meters elevation. A large portion of the area is located in the state of Pahang and another small portion is located in the state of Selangor. It was established by the late Chinese businessman, Lim Goh Tong in 1965. The primary tourist attraction is Resorts World Genting, a hill resort where casinos and theme parks are situated and gambling is allowed.", "Theme Parks", "Casino", "Shopping Malls", "Cable Car", "It is very cold, remember to bring on your jackets.", "You may experience ear barotrauma caused by air pressure changes at higher elevation. Try to swallow something to relieve the pain.");
        addLocation(db, "1", "3", "A Famosa", "The Fortress of Malacca was a Portuguese fortress built in Malacca, Malaysia, in 1512. The oldest part of the fortress, was a keep known as A Famosa. Some time following the Battle of Malacca and the occupation of the city by the Dutch, the keep was destroyed but the outer walls of the fortress were reinforced. However, in 1807 the British destroyed most of the fortress. The Porta de Santiago gateway, and the restored Middelburg Bastion are the only parts of the fortress which remain today, they are among the oldest European architectural remains in Southeast Asia and the Far East.", "Landmark and Historical Site", "St Paul's Church", "City Gate", "Rickshaw", "Do try out the chicken rice. It is like a rice balls!", "Remember to bring along your camera as it is one of the most photographed landmarks in the city.");
        addLocation(db, "1", "4", "Sunway Lagoon", "The Sunway Lagoon is a theme park in Bandar Sunway, Subang Jaya, Selangor, Malaysia owned by Sunway Group.", "2 Water Parks and 4 Dry Parks", "Bungee Jump", "Malls", "Suspension Bridge", "We'd suggest starting out at the dry parks first and then spend the rest of the day at the water parks.", "Add on a Quack Xpress Express Lane Pass and you'll be able to skip-the-queue at popular attractions in the park!");
        addLocation(db, "2", "1", "Universal Studios Singapore", "Universal Studios Singapore is a theme park located within Resorts World Sentosa on Sentosa Island, Singapore. It features 28 rides, shows, and attractions in seven themed zones. It attracted more than 2 million visitors in the 9 months from its opening. The park has been marketed by Universal Parks & Resorts as a \"one-of-its-kind theme park in Asia\" and promised that the park would be the only one of its kind in Southeast Asia for the next 30 years.", "Transformers", "The Mummy", "Waterworld", "Jurassic Park", "If you plan to enjoy the Transformer game, then save it for around 3 or 4 o’clock in the afternoon to avoid the crowds.", "If you don’t need to leave USS before night time, then save some more moments at the park for sunset. Night shows at USS are dazzling and fascinating. These are the three best shows that you and your kids will fall in love, let’s take a look!");
        addLocation(db, "2", "2", "Sentosa", "Sentosa Island, known mononymously as Sentosa, is an island located off the southern coast of Singapore's main island. It is now home to a popular resort that receives more than twenty million visitors per year. Attractions include a 2 km long sheltered beach, Madame Tussauds Singapore, an extensive Cable Car network, Fort Siloso, two golf courses, 14 hotels and the Resorts World Sentosa, which features the Universal Studios Singapore theme park and one of Singapore's two casinos, the other being Marina Bay Sands.", "White Sand", "Casino", "Aquarium", "Golf Courses", "First things first, you need to provide yourself with a Sentosa Island Map. This map covers all the sights on the island so you can easily explore the island. You can get this essential document at any Sentosa Express Monorail Stations, Cable Car Stations, and Sentosa Boardwalk―for free!", "One of the most convenient ways to get around the island is to take the Sentosa Express monorail. By paying SGD $4, you can transfer from Singapore to Sentosa Island with ease. Once you arrive on the island, you can hop from one station to another for free via the monorail.");
        addLocation(db, "2", "3", "Gardens by the Bay", "The Gardens by the Bay is a urban nature park spanning 110 hectares within the Marina Bay district of the Central Region of Singapore, adjacent to the Marina Reservoir. The park consists of three waterfront gardens: Bay South Garden, Bay East Garden and Bay Central Garden. The largest of the gardens is the Bay South Garden at 54 hectares designed by Grant Associates. Its Flower Dome is the largest glass greenhouse in the world. Gardens by the Bay was part of the nation's plans to transform its \"Garden City\" to a \"City in a Garden\", with the aim of raising the quality of life by enhancing greenery and flora in the city.", "Light Show", "Skywalk", "Nighscape", "Rain Forest", "Bring a light jacket – the conservatories can get cold.", "Have a wide-angle lens to shoot (recommendation: 16-19mm lens or GoPro) – to get that ‘long’ water-fall shot.");
        addLocation(db, "2", "4", "Merlion", "The Merlion Park is a Singaporean landmark and a major tourist attraction located in the Downtown Core district of Singapore, near its Central Business District. The Merlion is a mythical creature with a lion's head and the body of a fish that is widely used as a mascot and national personification of Singapore. Two Merlion statues are located at the park. The original Merlion structure measures 8.6 meters tall and spouts water from its mouth. It has subsequently been joined by a Merlion cub, which is located near the original statue and measures just 2 metres tall.", "Icon of Singapore", "Built Heritage", "Light Show", "Bumboat Ride", "Just always remember to wear a hat or bring an umbrella, because its so hot out there.", "Do not plan your trip to Merlion Park during the period between October and January as your plans will be hampered with the intermittent showers and rains.");
        addLocation(db, "3", "1", "Mount Fuji", "Japan’s Mt. Fuji is an active volcano about 100 kilometers southwest of Tokyo. Commonly called “Fuji-san,” it’s the country’s tallest peak, at 3,776 meters. A pilgrimage site for centuries, it’s considered one of Japan’s 3 sacred mountains, and summit hikes remain a popular activity. Its iconic profile is the subject of numerous works of art, notably Edo Period prints by Hokusai and Hiroshige.", "World of Art", "Shinkansen", "Sunset", "Climbing", "Make sure you have a waterproof jacket and pants. Since Mt Fuji has its own weather system, it can rain at any moment. Unusually strong wind is also a factor so it’s important to stay dry.", "Bring warm clothes for the wait at the top. After you reach the top there will be a wait in the pre-dawn cold before sunrise, so bringing warm clothes including gloves and a hat is critical.");
        addLocation(db, "3", "2", "Itsukushima Shrine", "Itsukushima Shrine is a Shinto shrine on the island of Itsukushima, best known for its \"floating\" torii gate. It is in the city of Hatsukaichi in Hiroshima Prefecture in Japan. The shrine complex is listed as a UNESCO World Heritage Site, and the Japanese government has designated several buildings and possessions as National Treasures. The Itsukushima shrine is one of Japan's most popular tourist attractions. It is most famous for its dramatic gate, or torii on the outskirts of the shrine, the sacred peaks of Mount Misen, extensive forests, and its ocean view.", "Torii", "View", "Tide", "Wild Deer", "Try to see the torii gate at both low and high tide - Check the tide times in advance and plan your day accordingly.", "Watch out for the deer - Deer are herbivores, so feeding them is discouraged, and it’s best to avoid littering the ground with food that they might snaffle for themselves.");
        addLocation(db, "3", "3", "Osaka Castle", "Osaka Castle is a Japanese castle in Chūō-ku, Osaka, Japan. The castle is one of Japan's most famous landmarks and it played a major role in the unification of Japan during the sixteenth century of the Azuchi-Momoyama period", "Toyotomi Hideyoshi", "Siege of Osaka", "Samurai", "Sakura", "Now visitors to Osaka-jo can take 60- to 90-minute classes learning sword fighting and theatrical combat techniques. Best of all, you get to dress the part and look like a character straight out of a Japanese period drama.", "One of Japan’s best winter light-up events is the Osaka Castle Illuminage. Held in Nishinomaru Garden on the castle grounds in the evenings between December and March, millions of colourful LED lights illuminate the area.");
        addLocation(db, "3", "4", "Akihabara", "Akihabara is a buzzing shopping hub famed for its electronics retailers, ranging from tiny stalls to vast department stores like Yodobashi Multimedia Akiba. Venues specializing in manga, anime, and video games include Tokyo Anime Center, for exhibits and souvenirs, and Radio Kaikan with 10 floors of toys, trading cards, and collectibles. Staff dressed as maids or butlers serve tea and desserts at nearby maid cafes.", "Anime Periphery", "Maid Cafe", "Gacha", "Shopping", "Do not shop at the department / consignment stores close to the station (Unless you are willing to pay a premium price!)", "Visit this city on a Sunday. Popularly known for their huge options of eight-storey department stores full of Anime figurines and Otaku-like collectibles, Akihabara also boasts a culture of friendship, freedom, peace and family-fun.");
        addLocation(db, "4", "1", "Hong Kong Disneyland", "Hong Kong Disneyland is a theme park located on reclaimed land in Penny's Bay, Lantau Island. It is located inside the Hong Kong Disneyland Resort and it is owned and managed by Hong Kong International Theme Parks. It is the largest theme park in Hong Kong, followed by Ocean Park Hong Kong.", "Parade", "Fireworks", "Theme Parks", "Space Mountain", "The Disneyland FASTPASS lets you bypass the standby line – that is, the main queue at attractions – to get first priority for your rides with minimum wait time.", "Meeting the Disney characters is insanely popular in Hong Kong Disneyland. After all, how often do you get to take a photo with your childhood tv heroes?");
        addLocation(db, "4", "2", "Ocean Park", "Ocean Park Hong Kong, commonly known as Ocean Park, is a marine mammal park, oceanarium, animal theme park and amusement park situated in Wong Chuk Hang and Nam Long Shan in the Southern District of Hong Kong. It is the second largest theme park in Hong Kong, after Hong Kong Disneyland.", "Panda", "Dolphin", "Penguins", "Aquarium", "Buy Ocean FasTrack tickets for all the rides of the Ocean Park Hong Kong as you can get priority access to all the rides with it for just a small additional cost.", "Head to the Giant Panda Adventure first thing in the morning as this is the feeding time for the two giant pandas.");
        addLocation(db, "4", "3", "Lan Kwai Fong", "Lan Kwai Fong is a small square of streets in Central, Hong Kong. The area was dedicated to hawkers before the Second World War, but underwent a renaissance in the mid-1980s. It is now a popular expatriate haunt in Hong Kong for drinking, clubbing and dining. The street Lan Kwai Fong is L-shaped with two ends joining with D'Aguilar Street.", "Bar Street", "Night Life", "Party", "Street Show", "Every Halloween, citizens and tourists will dress up as different characters and come to Lan Kwai Fong to celebrate Halloween. This is the only free outdoor party venue in Hong Kong.", "Very crowded especially during weekends. Advised to take care as a little complicated area.");
        addLocation(db, "4", "4", "The Peak Tram", "The Peak Tram is a funicular railway in Hong Kong, which carries both tourists and residents to the upper levels of Hong Kong Island. Running from Garden Road Admiralty to Victoria Peak via the Mid-Levels, it provides the most direct route and offers good views over the harbour and skyscrapers of Hong Kong.", "Victoria Peak", "Nightscape", "Funicular", "Fog", "Weekends and public holidays are going to be the busiest times to visit. If you can schedule your itinerary to go on a weekday, the lines will be much shorter.", "Octopus Cards (the swipe cash card for Hong Kong transport) are accepted for the Peak Tram and avoid the ticketing queue. However at busy times, it’s the line for the tram itself that is long so paying by Octopus won’t actually be much of an advantage.");
    }

    private void addLocation(SQLiteDatabase db, String c, String l, String t, String s, String ts1, String ts2, String ts3, String ts4, String t1, String t2){
        ContentValues cv = new ContentValues();
        cv.put("country", c);
        cv.put("location", l);
        cv.put("title", t);
        cv.put("summary", s);
        cv.put("ts1", ts1);
        cv.put("ts2", ts2);
        cv.put("ts3", ts3);
        cv.put("ts4", ts4);
        cv.put("t1", t1);
        cv.put("t2", t2);
        db.insert("Location", null, cv);
    }

    private void fillUserTable(SQLiteDatabase db){
        addUser(db, "bookman@xmu.edu.my", "Bookman", "bookman");
        addUser(db, "superman@xmu.edu.my", "SuperMan", "superman");
        addUser(db, "pokemon@xmu.edu.my", "Pokemon", "pokemon");
        addUser(db, "hanmeimei@xmu.edu.my", "Han Mei Mei", "hanmeimei");
        addUser(db, "lilei@xmu.edu.my", "Li Lei", "lilei");
    }

    private void addUser(SQLiteDatabase db, String email, String name, String password){
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("name", name);
        cv.put("password", password);
        db.insert("User", null, cv);
    }

    private void fillReviewTable(SQLiteDatabase db){
        addReview(db, 5, "Second visit in a long time. Pleased to see that facilities are maintained. Staff & service are top notch.  We went during lunchtime so we practically had the viewing decks to ourselves.  Information is available everywhere with ample signage & staff eager to answer your queries.", "1", "1", 2);
        addReview(db, 5, "Petronas Twin Towers - tallest twin tower building in the world.  Looks great during the day and amazing in the evening.  The building is fully used for the offices of well-known oil companies", "1", "1", 3);
        addReview(db, 3, "The Signatures food court has been refurbished to look better but has also impact the variety of food establishments. I can no longer get a good spread of Malaysian food anymore, just those fancy burgers, hot dogs, etc. are available", "1", "1", 4);
        addReview(db, 4, "Location where parts of the movie Entrapment were shot starred by Sean Connery and Catherine Zeta Jones. Movie stars aside, the connecting bridge between the twin towers offers a great view of the surroundings.", "1", "1", 5);
    }

    private void addReview(SQLiteDatabase db, int rating, String review, String country, String location, int user){
        ContentValues cv = new ContentValues();
        cv.put("rating", rating);
        cv.put("review", review);
        cv.put("country", country);
        cv.put("location", location);
        cv.put("user", user);
        db.insert("Review", null, cv);
    }

    //Get Information about the Culture
    public Cursor getCultureData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Culture where id = ?", new String[] {id});
        return res;
    }

    //Get Information about the Location
    public Cursor getLocationData(String country, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Location where country = ? and location = ?", new String[] {country, location});
        return res;
    }

    //insert User data when register - return boolean to know success or not
    public Boolean addUser(String email, String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("name", name);
        cv.put("password", password);
        long result = db.insert("User", null, cv);

        // failed to insert will return -1
        if(result == -1)
            return false;
        else
            return true;
    }

    //Check if email or password has existed - to keep uniqueness
    public Boolean checkUniqueness(String email, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE email = ? OR name = ?",new String[]{email, name});

        //true if duplicate id
        return cursor.getCount() > 0;
    }

    //To check password & return userId that logged in
    public int checkCredential(String name, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM User WHERE name = ? AND password = ? ",new String[]{name, password});
        if(cursor.getCount() > 0)
        {
            cursor.moveToNext();
            return cursor.getInt(cursor.getColumnIndex("id"));
        }
        else
        {
            return 0;
        }
    }

    //Get Information about the User - Here only registered date info because name and id already stored in session
    //Because when create the User, we let the registeredDate to be default
    //But in sqlite, it will automatically convert to UTC
    //Hence when retrieving registeredDate, I convert back to localtime
    public Cursor getUserData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT date(registeredDate, 'localtime') as registeredDate FROM User WHERE id = ?", new String[] {id});
        return res;
    }

    //insert Review data - return boolean to know success or not
    public Boolean addReview(int rating, String review, String country, String location, int user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rating", rating);
        cv.put("review", review);
        cv.put("country", country);
        cv.put("location", location);
        cv.put("user", user);
        long result = db.insert("Review", null, cv);

        // failed to insert will return -1
        if(result == -1)
            return false;
        else
            return true;
    }

    //Get Review + User date for ReviewFragment - many reviews from different users
    //datetime(latestDate, 'localtime') - to show device's timezone
    public Cursor getReviewData(String country, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT img, rating, review, name, datetime(latestDate, 'localtime') as latestDate FROM Review LEFT JOIN User ON Review.user = User.id WHERE Review.country = ? AND Review.location = ? ORDER BY Review.latestDate DESC", new String[] {country, location});
        return res;
    }

    //Get Review for IntroFragment - only the logged in user's review
    //datetime(latestDate, 'localtime') - to show device's timezone
    public Cursor isRated(String country, String location, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT rating, datetime(latestDate, 'localtime') as latestDate, review FROM Review WHERE country = ? AND location = ? AND user = ?", new String[] {country, location, id});
        return cursor;
    }

    //Update Review
    public boolean updateReview(String country, String location, String id, int rating, String review, String now){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rating", rating);
        cv.put("review", review);
        cv.put("latestDate", now);
        long result = db.update("Review", cv, "country = ? and location = ? and user = ?", new String[] {country, location, id});

        if(result == -1)
            return false;
        else
            return true;
    }

    //Delete review
    public boolean deleteReview(String country, String location, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Review", "country = ? and location = ? and user = ?", new String[] {country, location, id});
        if(result == -1)
            return false;
        else
            return true;
    }

    //Get count of each star
    public Cursor getRatingSummary(String country, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT rating, COUNT(*) as count FROM Review WHERE country = ? AND location = ? GROUP BY country, location, rating", new String[] {country, location});
        return res;
    }

    //Update User's profile image
    public Boolean updateImage(String x, Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            FileInputStream fs = new FileInputStream(x);
            byte[] imgByte = new byte[fs.available()];
            fs.read(imgByte);
            ContentValues cv = new ContentValues();
            cv.put("img", imgByte);
            db.update("User", cv, "id = ?", new String[] {id.toString()});
            fs.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Retrieve profile image
    public Bitmap getImage(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bt = null;
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE id = ?", new String[] {id});
        if(cursor.moveToNext()) {
            byte[] img = cursor.getBlob(cursor.getColumnIndex("img"));
            if (img != null)
                bt = BitmapFactory.decodeByteArray(img, 0, img.length);
        }
        return bt;
    }
}
