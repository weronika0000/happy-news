# happy-news
Happy News is a CRUD Java-based web application built on the Spring Framework. Empowering users to share and explore positive news articles.


# Installation
1. Clone the repository to your local machine.
2. Open the project in your preferred IDE.
3. Ensure you have Java and Maven installed.
4. Build the project using Maven: `mvn clean install`.
5. Run the application using Maven: `mvn spring-boot:run`.
6. Access the application through your web browser at http://localhost:8080.

# Usage
1. Register a new account or log in if you already have an account.
2. Browse articles by category or use the search functionality to find specific articles.
3. Click on an article to view the full content.
4. If there is content you want to share, you can add it. Verification by admin will be done before publishing.
4. If you are the author, you can edit or delete your own articles.
5. Add comments to articles to share your thoughts and engage with other users.
6. You can subscribe to the category you like and get notified when there is a new article.

# Features

1. Security
    - User registration: Users can create an account to access the application.
    - Login system: Registered users can log in to the application.
    - Role-based access: Different roles are assigned to users, allowing different levels of access.
    - Password management: Users can change their password, confirm password changes, and reset their password if forgotten.

2. Admin
    - Article management: Admins can add, delete, and edit articles.
    - Article review: Admins can accept or edit articles submitted by users.
    - Comment management: Admins can delete comments made by users.

3. User
    - Profile editing: Users can edit their profile information.
    - User can browse and send articles 
    - User can comment existing articles

4. Article Browsing and Reading
    - Browsing articles: Users can browse through a list of articles.
    - Full article view: Clicking on an article on the main page directs users to the full article.

5. Article Submission
    - User article submission: Logged-in users can submit articles for review and publishing.
    - Article approval: When a user submits an article, the admin receives a notification and must accept it for publishing.
    - Article publishing: If the admin approves the article, it is published on the platform.

6.  Comments
    - User comments: Users can add comments to articles.
    - Comment replies: Users can add comments to existing comments, allowing one level of reply.

7. Search Functionality
    - Article search: Users can search for articles based on filters such as category, location, author
8. Categories for Articles
    - Categorization: Articles are categorized into different topics, including People, Culture, Environment, Science, Economics, and Lifestyle.

9. Notifications
    - Admin notifications: Admins receive notifications when a user submits an article for approval.
    - User notifications: Users receive notifications regarding the acceptance or rejection of their submitted articles.

10. Recommendations
    - User recommendations: Provide a section for users with recommended articles based on their favorite category.
    - User subscriptions: Allow users to subscribe to specific authors and receive notifications when they publish new articles.
    - Related articles: Display related articles on the article web page, either by generated links or by showing articles that are connected.
    - Top Articles: Display a list of articles based on the number of comments or views on the index page.

# Technologies used:
Java, SpringBoot, JPA, Hibernate, JUnit, Mockito, JSP

MVC architecture implemented using Spring Web-MVC

# Authors
Weronika Konior,
Vladyslav Parfan