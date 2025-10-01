# Release Calendar Application - Development Roadmap

**Project Goal:** Build a web-based release calendar to track software release milestones, with admin interface, Gantt chart visualization, and Slack notifications.

**Learning Path:** Incremental development alongside MOOC.FI Java course, progressing from console app to production-ready Spring Boot application.

---

## Phase 1: Java Fundamentals (MOOC Part 1-2, Weeks 1-7)
**Focus: Core Java concepts before Spring**

### Milestone 1.1: Domain Models (Week 1-2)
**Status:** 🚧 In Progress
**Goal:** Create the core business objects

**Tasks:**
- [ ] Create `Release` class with fields: id, name, version, startDate, endDate
- [ ] Create `Milestone` class with fields: id, name, date, type, releaseId
- [ ] Implement constructors, getters, setters, toString()
- [ ] Add basic validation logic (dates in correct order, non-null fields)

**Skills Practiced:** Classes, objects, encapsulation, constructors

**Deliverable:** Two working domain classes with validation

---

### Milestone 1.2: Collections & Data Management (Week 3-4)
**Status:** 🔲 Not Started

**Goal:** Manage collections of releases and milestones in memory

**Tasks:**
- [ ] Create `ReleaseManager` class using ArrayList to store releases
- [ ] Implement methods: addRelease(), getRelease(), getAllReleases(), updateRelease(), deleteRelease()
- [ ] Create `MilestoneManager` class with similar CRUD methods
- [ ] Link milestones to their parent releases
- [ ] Handle edge cases (duplicate IDs, null values, etc.)

**Skills Practiced:** ArrayList, iteration, searching, CRUD operations

**Deliverable:** Two manager classes with full CRUD functionality

---

### Milestone 1.3: Console Application (Week 5-7)
**Status:** 🔲 Not Started

**Goal:** Build a text-based user interface

**Tasks:**
- [ ] Create `UserInterface` class with Scanner for input
- [ ] Build menu system with options (Add Release, View Releases, Add Milestone, etc.)
- [ ] Implement all CRUD operations through the menu
- [ ] Display releases and milestones in formatted text
- [ ] Calculate and display days until next milestone
- [ ] Add input validation and error messages

**Skills Practiced:** Scanner input, control flow, method organization, user interaction

**Deliverable:** Working console app that manages releases and milestones in memory

---

## Phase 2: Spring Boot Basics (Post-MOOC Part 1, Week 8-10)
**Focus: Transition to web framework**

### Milestone 2.1: Spring Boot Setup (Week 8)
**Status:** 🔲 Not Started

**Goal:** Set up Spring Boot project structure

**Tasks:**
- [ ] Install Java Development Kit (JDK 17+)
- [ ] Install Maven or Gradle
- [ ] Use Spring Initializr to create project
- [ ] Add dependencies: Spring Web, Spring Boot DevTools
- [ ] Create basic project structure following Maven conventions
- [ ] Run "Hello World" REST endpoint to verify setup
- [ ] Configure application.properties

**Skills Learned:** Spring Boot basics, project structure, Maven/Gradle, REST fundamentals

**Deliverable:** Running Spring Boot application with test endpoint

---

### Milestone 2.2: REST API - First Endpoints (Week 9)
**Status:** 🔲 Not Started

**Goal:** Create REST endpoints for releases

**Tasks:**
- [ ] Create `ReleaseController` class with @RestController annotation
- [ ] Refactor Phase 1 Release class for use with Spring
- [ ] Implement endpoints:
    - `GET /api/releases` - list all releases
    - `GET /api/releases/{id}` - get single release
    - `POST /api/releases` - create new release
    - `PUT /api/releases/{id}` - update release
    - `DELETE /api/releases/{id}` - delete release
- [ ] Use in-memory ArrayList for storage (from Phase 1)
- [ ] Test with Postman or browser
- [ ] Return proper HTTP status codes (200, 201, 404, etc.)

**Skills Learned:** REST principles, HTTP methods, JSON, Spring annotations

**Deliverable:** REST API for releases, testable via Postman

---

### Milestone 2.3: Milestone Endpoints (Week 10)
**Status:** 🔲 Not Started

**Goal:** Add REST endpoints for milestones

**Tasks:**
- [ ] Create `MilestoneController` class
- [ ] Implement full CRUD endpoints for milestones
- [ ] Add endpoint to get all milestones for a specific release
- [ ] Link milestones to releases via releaseId
- [ ] Handle relationships and cascading operations
- [ ] Add request/response validation

**Skills Learned:** @PathVariable, @RequestBody, ResponseEntity, REST relationships

**Deliverable:** Complete REST API with in-memory storage

---

## Phase 3: Database Integration (Week 11-14)
**Focus: Persistent storage with JPA**

### Milestone 3.1: Database Setup (Week 11)
**Status:** 🔲 Not Started

**Goal:** Add database persistence

**Tasks:**
- [ ] Add dependencies: Spring Data JPA, H2 Database
- [ ] Configure application.properties for database connection
- [ ] Convert Release to JPA entity (@Entity, @Id, @GeneratedValue)
- [ ] Convert Milestone to JPA entity
- [ ] Add @Column annotations and constraints
- [ ] Test database creation and connection

**Skills Learned:** JPA/Hibernate, entity mapping, database configuration

**Deliverable:** JPA entities with working database connection

---

### Milestone 3.2: Repositories (Week 12)
**Status:** 🔲 Not Started

**Goal:** Replace in-memory storage with database repositories

**Tasks:**
- [ ] Create `ReleaseRepository` interface extending JpaRepository<Release, Long>
- [ ] Create `MilestoneRepository` interface
- [ ] Remove ArrayList storage from controllers
- [ ] Use repositories for all CRUD operations
- [ ] Add custom query methods (findByName, findByDateBetween, etc.)

**Skills Learned:** Spring Data JPA, repository pattern, custom queries

**Deliverable:** REST API with database persistence

---

### Milestone 3.3: Service Layer (Week 13)
**Status:** 🔲 Not Started

**Goal:** Add proper layering and business logic

**Tasks:**
- [ ] Create `ReleaseService` class with @Service annotation
- [ ] Create `MilestoneService` class
- [ ] Move business logic from controllers to services
- [ ] Add validation logic (date checks, duplicate prevention)
- [ ] Implement proper error handling
- [ ] Controllers now call services, services call repositories

**Skills Learned:** Separation of concerns, service layer pattern, business logic

**Deliverable:** Three-layer architecture (Controller → Service → Repository)

---

### Milestone 3.4: Relationships (Week 14)
**Status:** 🔲 Not Started

**Goal:** Implement JPA relationships

**Tasks:**
- [ ] Add @OneToMany relationship: Release → Milestones
- [ ] Add @ManyToOne relationship: Milestone → Release
- [ ] Configure cascade operations (CascadeType.ALL)
- [ ] Add fetch types (LAZY vs EAGER)
- [ ] Query milestones by release
- [ ] Handle orphan removal

**Skills Learned:** JPA relationships, @OneToMany, @ManyToOne, cascading

**Deliverable:** Full relational data model with proper associations

---

## Phase 4: Web UI - Admin Interface (Week 15-18)
**Focus: Server-side rendering with Thymeleaf**

### Milestone 4.1: Thymeleaf Setup (Week 15)
**Status:** 🔲 Not Started

**Goal:** Add web page rendering

**Tasks:**
- [ ] Add Spring Boot Starter Thymeleaf dependency
- [ ] Create `WebController` for page routing
- [ ] Create base template with header/footer
- [ ] Build homepage: list all releases in HTML table
- [ ] Add Bootstrap CSS for styling
- [ ] Test page rendering

**Skills Learned:** Thymeleaf basics, template engine, th:each, th:text

**Deliverable:** Homepage displaying releases

---

### Milestone 4.2: Release Management Pages (Week 16)
**Status:** 🔲 Not Started

**Goal:** Full CRUD web interface for releases

**Tasks:**
- [ ] Create "Add Release" form page
- [ ] Create "Edit Release" form page
- [ ] Create "View Release Details" page
- [ ] Handle form submission with POST
- [ ] Add date pickers for date fields
- [ ] Add validation feedback on forms
- [ ] Implement delete confirmation

**Skills Learned:** Thymeleaf forms, th:object, th:field, form handling

**Deliverable:** Complete web interface for managing releases

---

### Milestone 4.3: Milestone Management (Week 17)
**Status:** 🔲 Not Started

**Goal:** Web interface for milestones

**Tasks:**
- [ ] Add milestone section to release detail page
- [ ] Create form to add milestones to a release
- [ ] Display milestones in table/card format
- [ ] Add edit/delete buttons for each milestone
- [ ] Create dropdown for milestone type selection
- [ ] Color-code milestones by type

**Skills Learned:** Nested forms, dynamic content, navigation

**Deliverable:** Full milestone management within release pages

---

### Milestone 4.4: Validation & Error Handling (Week 18)
**Status:** 🔲 Not Started

**Goal:** Robust validation and user feedback

**Tasks:**
- [ ] Add Bean Validation annotations (@NotNull, @NotBlank, @Future)
- [ ] Display validation errors on forms
- [ ] Create custom validators for business rules
- [ ] Add user-friendly error messages
- [ ] Handle 404 and 500 errors with custom pages
- [ ] Add success messages after operations

**Skills Learned:** @Valid, BindingResult, custom validators, error pages

**Deliverable:** Polished UI with comprehensive validation

---

## Phase 5: Calendar & Gantt View (Week 19-21)
**Focus: Visualization of timeline data**

### Milestone 5.1: List View Enhancements (Week 19)
**Status:** 🔲 Not Started

**Goal:** Improved calendar-style display

**Tasks:**
- [ ] Create "All Releases" calendar view page
- [ ] Sort releases by date (upcoming, current, completed)
- [ ] Add status badges (upcoming, in progress, completed, overdue)
- [ ] Implement date range filter
- [ ] Add search/filter functionality
- [ ] Color-code releases by status

**Skills Learned:** Sorting, filtering, conditional styling

**Deliverable:** Enhanced list view with filtering

---

### Milestone 5.2: Gantt Chart Integration (Week 20-21)
**Status:** 🔲 Not Started

**Goal:** Timeline visualization with Gantt chart

**Tasks:**
- [ ] Research and choose Gantt library (Chart.js, DHTMLX Gantt, or Frappe Gantt)
- [ ] Create REST endpoint: `GET /api/gantt-data` returning formatted data
- [ ] Build Gantt chart page
- [ ] Display multiple releases on timeline
- [ ] Show milestones as markers on timeline
- [ ] Add interactivity (zoom, pan, tooltip on hover)
- [ ] Make responsive for different screen sizes

**Skills Learned:** JavaScript integration, data formatting, charting libraries

**Deliverable:** Interactive Gantt chart showing all releases and milestones

---

## Phase 6: Security & User Management (Week 22-25)
**Focus: Authentication and authorization**

### Milestone 6.1: Spring Security Setup (Week 22)
**Status:** 🔲 Not Started

**Goal:** Add authentication

**Tasks:**
- [ ] Add Spring Security dependency
- [ ] Create SecurityConfig class with @EnableWebSecurity
- [ ] Configure HTTP security (which pages need auth)
- [ ] Create custom login page
- [ ] Test with in-memory users
- [ ] Protect admin routes

**Skills Learned:** Spring Security basics, authentication, security configuration

**Deliverable:** Login system with basic authentication

---

### Milestone 6.2: User Roles (Week 23)
**Status:** 🔲 Not Started

**Goal:** Role-based access control

**Tasks:**
- [ ] Create User entity with username, password, roles
- [ ] Create Role entity (ADMIN, USER)
- [ ] Create UserRepository
- [ ] Define role hierarchy (ADMIN can do everything, USER read-only)
- [ ] Implement UserDetailsService
- [ ] Add @PreAuthorize annotations to methods
- [ ] Test role restrictions

**Skills Learned:** UserDetailsService, role-based security, @PreAuthorize

**Deliverable:** Two-tier access control (Admin/User)

---

### Milestone 6.3: Registration & User Management (Week 24-25)
**Status:** 🔲 Not Started

**Goal:** Complete user management system

**Tasks:**
- [ ] Create admin page to manage users (list, create, edit, delete)
- [ ] Add user registration page (optional, based on needs)
- [ ] Implement password encoding with BCrypt
- [ ] Add password change functionality
- [ ] Create user profile page
- [ ] Add "created by" and "last modified by" audit fields

**Skills Learned:** Password security, BCrypt, user management patterns

**Deliverable:** Secured application with complete user management

---

## Phase 7: Notifications & Automation (Week 26-29)
**Focus: Slack integration and scheduled tasks**

### Milestone 7.1: Slack Webhook Setup (Week 26)
**Status:** 🔲 Not Started

**Goal:** Send messages to Slack

**Tasks:**
- [ ] Create Slack app and generate webhook URL
- [ ] Add Slack webhook URL to application.properties
- [ ] Create `SlackService` class
- [ ] Implement method to send test message
- [ ] Create Notification entity to log sent notifications
- [ ] Test manual notification sending

**Skills Learned:** External API integration, RestTemplate/WebClient, webhooks

**Deliverable:** Ability to send messages to Slack channel

---

### Milestone 7.2: Notification Logic (Week 27)
**Status:** 🔲 Not Started

**Goal:** Smart notification system

**Tasks:**
- [ ] Create `NotificationService` class
- [ ] Design notification message templates for each milestone type
- [ ] Add "Send Notification" button in admin UI
- [ ] Implement notification for specific releases/milestones
- [ ] Store notification history in database
- [ ] Display notification log in admin panel

**Skills Learned:** Template patterns, audit logging, service integration

**Deliverable:** Manual notification system with history

---

### Milestone 7.3: Scheduled Notifications (Week 28)
**Status:** 🔲 Not Started

**Goal:** Automatic notifications

**Tasks:**
- [ ] Add @EnableScheduling to application
- [ ] Create `NotificationScheduler` class
- [ ] Implement scheduled task to check upcoming milestones
- [ ] Configure notification rules (7 days before, 1 day before, day of)
- [ ] Send automatic reminders based on rules
- [ ] Add cron expression for daily checks
- [ ] Test scheduled execution

**Skills Learned:** @Scheduled, cron expressions, background tasks

**Deliverable:** Automated notification system

---

### Milestone 7.4: Notification Management (Week 29)
**Status:** 🔲 Not Started

**Goal:** Configure notification preferences

**Tasks:**
- [ ] Create admin page for notification settings
- [ ] Add enable/disable toggle per release
- [ ] Add notification preference fields (channels, timing, recipients)
- [ ] Create notification logs view with filtering
- [ ] Add retry logic for failed notifications
- [ ] Implement notification summary dashboard

**Skills Learned:** Configuration management, admin features, dashboard design

**Deliverable:** Fully configurable notification system

---

## Phase 8: Polish & Production Ready (Week 30-32)
**Focus: Deployment and refinement**

### Milestone 8.1: Switch to Production Database (Week 30)
**Status:** 🔲 Not Started

**Goal:** Use production-grade database

**Tasks:**
- [ ] Choose database (PostgreSQL or MySQL)
- [ ] Install database locally
- [ ] Update application.properties for new database
- [ ] Create database migration scripts using Flyway
- [ ] Migrate data from H2 to production database
- [ ] Configure connection pooling
- [ ] Test all functionality with new database

**Skills Learned:** PostgreSQL/MySQL, database migration, Flyway

**Deliverable:** Application running on production database

---

### Milestone 8.2: Error Handling & Logging (Week 31)
**Status:** 🔲 Not Started

**Goal:** Production-ready error handling

**Tasks:**
- [ ] Create GlobalExceptionHandler with @ControllerAdvice
- [ ] Add custom exception classes
- [ ] Implement proper logging with SLF4J
- [ ] Create user-friendly error pages (404, 500)
- [ ] Log important events (user actions, errors, notifications)
- [ ] Configure log levels (dev vs production)

**Skills Learned:** @ControllerAdvice, logging best practices, exception handling

**Deliverable:** Robust error handling and logging

---

### Milestone 8.3: Testing (Week 32)
**Status:** 🔲 Not Started

**Goal:** Comprehensive test coverage

**Tasks:**
- [ ] Write unit tests for services using JUnit
- [ ] Write repository integration tests
- [ ] Write controller tests with MockMvc
- [ ] Add test coverage reporting
- [ ] Aim for 70%+ code coverage
- [ ] Document testing strategy

**Skills Learned:** JUnit, Mockito, @SpringBootTest, MockMvc

**Deliverable:** Well-tested application

---

### Milestone 8.4: Deployment (Week 32)
**Status:** 🔲 Not Started

**Goal:** Deploy to production

**Tasks:**
- [ ] Package application as executable JAR
- [ ] Choose deployment platform (Heroku, Railway, AWS, DigitalOcean)
- [ ] Configure environment variables
- [ ] Set up database on cloud provider
- [ ] Deploy application
- [ ] Configure domain name (optional)
- [ ] Set up SSL certificate
- [ ] Monitor application logs

**Skills Learned:** Deployment, cloud platforms, environment configuration

**Deliverable:** Live production application

---

## Progress Tracking

| Phase | Status | Start Date | Completion Date |
|-------|--------|------------|-----------------|
| Phase 1 | 🔲 Not Started | | |
| Phase 2 | 🔲 Not Started | | |
| Phase 3 | 🔲 Not Started | | |
| Phase 4 | 🔲 Not Started | | |
| Phase 5 | 🔲 Not Started | | |
| Phase 6 | 🔲 Not Started | | |
| Phase 7 | 🔲 Not Started | | |
| Phase 8 | 🔲 Not Started | | |

**Status Legend:**
- 🔲 Not Started
- 🚧 In Progress
- ✅ Complete

---

## Learning Resources

### Phase 1-2: Java Basics
- MOOC.FI Java Programming course (mooc.fi)
- Practice coding exercises daily

### Phase 3-4: Spring Boot
- Spring Boot official guides (spring.io/guides)
- Baeldung Spring tutorials (baeldung.com)
- Spring Boot Tutorial by Amigoscode (YouTube)

### Phase 5: Frontend
- Thymeleaf documentation (thymeleaf.org)
- Bootstrap documentation (getbootstrap.com)
- Chart.js documentation (chartjs.org)

### Phase 6: Security
- Spring Security official docs
- Baeldung Spring Security tutorials

### Phase 7-8: Advanced Topics
- Spring Scheduling documentation
- Slack API documentation (api.slack.com)
- Testing with Spring Boot guides

---

## Estimated Timeline

**Aggressive Pace:** 6-8 months (10-15 hours/week)
- Fastest path for motivated learners
- Requires consistent daily practice

**Comfortable Pace:** 10-12 months (5-10 hours/week)
- Balanced approach with time to absorb concepts
- Recommended for most learners

**Relaxed Pace:** 12-18 months (3-5 hours/week)
- Perfect for busy schedules
- More time for experimentation and side learning

---

## Tips for Success

1. ✅ **Complete each milestone fully** before moving to next
2. ✅ **Commit to Git after each milestone** with clear messages
3. ✅ **Test thoroughly** at each phase
4. ✅ **Refactor as you learn** better patterns
5. ✅ **Keep it simple** - add complexity gradually
6. ✅ **Document your code** as you learn
7. ✅ **Ask questions when stuck** (Stack Overflow, Reddit r/learnjava, r/SpringBoot)
8. ✅ **Take breaks** - learning takes time
9. ✅ **Celebrate wins** - each milestone is an achievement!

---

## Important Reminder

**The goal is to LEARN, not just to finish.**

This is a journey, not a race. Take your time and understand each concept. It's okay to spend extra time on difficult topics. It's okay to revisit earlier phases to refine your code. The best learning happens when you're comfortable and curious.

**You're building real skills that will serve you throughout your career!**

---

## Questions or Stuck?

When you get stuck:
1. Read the error message carefully
2. Google the error message
3. Check Stack Overflow
4. Review the relevant documentation
5. Take a break and come back fresh
6. Ask for help in online communities

Remember: Every developer gets stuck. It's part of the process!

---

**Last Updated:** October 2025
**Current Phase:** Phase 1 - Ready to Begin!