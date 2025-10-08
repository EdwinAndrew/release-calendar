# Release Calendar Application - Development Roadmap

**Project Goal:** Build a web-based release calendar to track software release milestones, with admin interface, Gantt chart visualization, and Slack notifications.

**Learning Path:** Incremental development alongside MOOC.FI Java course, progressing from console app to production-ready Spring Boot + React application.

---

## Phase 1: Java Fundamentals
**Focus: Core Java concepts before Spring**

### Milestone 1.1: Domain Models
**Status:** ✅ Complete  
**Goal:** Create the core business objects

**Tasks:**
- [x] Create `Release` class with fields: id, name, version, startDate, endDate
- [x] Create `Milestone` class with fields: id, name, date, type, releaseId
- [x] Implement constructors, getters, setters, toString()
- [x] Add basic validation logic (dates in correct order, non-null fields)

**Skills Practiced:** Classes, objects, encapsulation, constructors

**Deliverable:** Two working domain classes with validation

---

### Milestone 1.2: Collections & Data Management
**Status:** ✅ Complete

**Goal:** Manage collections of releases and milestones in memory

**Tasks:**
- [x] Create `ReleaseManager` class using ArrayList to store releases
- [x] Implement methods: addRelease(), getRelease(), getAllReleases(), updateRelease(), deleteRelease()
- [x] Create `MilestoneManager` class with similar CRUD methods
- [x] Link milestones to their parent releases
- [x] Handle edge cases (duplicate IDs, null values, etc.)

**Skills Practiced:** ArrayList, iteration, searching, CRUD operations

**Deliverable:** Two manager classes with full CRUD functionality

---

### Milestone 1.3: Console Application
**Status:** ✅ Complete

**Goal:** Build a text-based user interface

**Tasks:**
- [x] Create `UserInterface` class with Scanner for input
- [x] Build menu system with options (Add Release, View Releases, Add Milestone, etc.)
- [x] Implement all CRUD operations through the menu
- [x] Display releases and milestones in formatted text
- [x] Calculate and display days until next milestone
- [x] Add input validation and error messages

**Skills Practiced:** Scanner input, control flow, method organization, user interaction

**Deliverable:** Working console app that manages releases and milestones in memory

---

## Phase 2: Spring Boot Basics
**Focus: Transition to web framework**

### Milestone 2.1: Spring Boot Setup
**Status:** ✅ Complete

**Goal:** Set up Spring Boot project structure

**Tasks:**
- [x] Install Java Development Kit (JDK 17+)
- [x] Install Maven or Gradle
- [x] Use Spring Initializr to create project
- [x] Add dependencies: Spring Web, Spring Boot DevTools
- [x] Create basic project structure following Maven conventions
- [x] Run "Hello World" REST endpoint to verify setup
- [x] Configure application.properties

**Skills Learned:** Spring Boot basics, project structure, Maven/Gradle, REST fundamentals

**Deliverable:** Running Spring Boot application with test endpoint

---

### Milestone 2.2: REST API - First Endpoints
**Status:** ✅ Complete

**Goal:** Create REST endpoints for releases

**Tasks:**
- [x] Create `ReleaseController` class with @RestController annotation
- [x] Refactor Phase 1 Release class for use with Spring
- [x] Implement endpoints:
    - `GET /api/releases` - list all releases
    - `GET /api/releases/{id}` - get single release
    - `POST /api/releases` - create new release
    - `PUT /api/releases/{id}` - update release
    - `DELETE /api/releases/{id}` - delete release
- [x] Use in-memory ArrayList for storage (from Phase 1)
- [x] Test with Postman or browser
- [x] Return proper HTTP status codes (200, 201, 404, etc.)

**Skills Learned:** REST principles, HTTP methods, JSON, Spring annotations

**Deliverable:** REST API for releases, testable via Postman

---

### Milestone 2.3: Milestone Endpoints
**Status:** ✅ Complete

**Goal:** Add REST endpoints for milestones

**Tasks:**
- [x] Create `MilestoneController` class
- [x] Implement full CRUD endpoints for milestones
- [x] Add endpoint to get all milestones for a specific release
- [x] Link milestones to releases via releaseId
- [x] Handle relationships and cascading operations
- [x] Add request/response validation

**Skills Learned:** @PathVariable, @RequestBody, ResponseEntity, REST relationships

**Deliverable:** Complete REST API with in-memory storage

---

## Phase 3: Database Integration
**Focus: Persistent storage with JPA**

### Milestone 3.1: Database Setup
**Status:** ✅ Complete

**Goal:** Add database persistence

**Tasks:**
- [x] Add dependencies: Spring Data JPA, H2 Database
- [x] Configure application.properties for database connection
- [x] Convert Release to JPA entity (@Entity, @Id, @GeneratedValue)
- [x] Convert Milestone to JPA entity
- [x] Add @Column annotations and constraints
- [x] Test database creation and connection

**Skills Learned:** JPA/Hibernate, entity mapping, database configuration

**Deliverable:** JPA entities with working database connection

---

### Milestone 3.2: Repositories
**Status:** ✅ Complete

**Goal:** Replace in-memory storage with database repositories

**Tasks:**
- [x] Create `ReleaseRepository` interface extending JpaRepository<Release, Long>
- [x] Create `MilestoneRepository` interface
- [x] Remove ArrayList storage from controllers
- [x] Use repositories for all CRUD operations
- [x] Add custom query methods (findByName, findByDateBetween, etc.)

**Skills Learned:** Spring Data JPA, repository pattern, custom queries

**Deliverable:** REST API with database persistence

---

### Milestone 3.3: Service Layer
**Status:** ✅ Complete

**Goal:** Add proper layering and business logic

**Tasks:**
- [x] Create `ReleaseService` class with @Service annotation
- [x] Create `MilestoneService` class
- [x] Move business logic from controllers to services
- [x] Add validation logic (date checks, duplicate prevention)
- [x] Implement proper error handling
- [x] Controllers now call services, services call repositories

**Skills Learned:** Separation of concerns, service layer pattern, business logic

**Deliverable:** Three-layer architecture (Controller → Service → Repository)

---

### Milestone 3.4: Relationships
**Status:** ✅ Complete

**Goal:** Implement JPA relationships

**Tasks:**
- [x] Add @OneToMany relationship: Release → Milestones
- [x] Add @ManyToOne relationship: Milestone → Release
- [x] Configure cascade operations (CascadeType.ALL)
- [x] Add fetch types (LAZY vs EAGER)
- [x] Query milestones by release
- [x] Handle orphan removal
- [x] Create MilestoneRequest DTO for API input
- [x] Test bidirectional navigation and cascade delete

**Skills Learned:** JPA relationships, @OneToMany, @ManyToOne, cascading, DTO pattern

**Deliverable:** Full relational data model with proper associations

---

## Phase 4: React Frontend - Admin Interface
**Focus: Modern client-side UI with Material-UI**

**Tech Stack:** React 18, Vite, Material-UI (MUI), Axios, React Router, Frappe Gantt

### Milestone 4.1: Project Setup & Configuration
**Status:** 🔲 Not Started  
**Goal:** Set up React development environment and connect to backend

**Tasks:**
- [x] Create React app using Vite: `npm create vite@latest release-calendar-frontend -- --template react`
- [x] Install core dependencies:
    - [x] Material-UI: `npm install @mui/material @emotion/react @emotion/styled`
    - [x] Material-UI Icons: `npm install @mui/icons-material`
    - [x] React Router: `npm install react-router-dom`
    - [x] Axios: `npm install axios`
    - [x] Date handling: `npm install date-fns`
- [x] Set up project folder structure (components, pages, services)
- [x] Configure CORS in Spring Boot backend:
  ```java
  @Configuration
  public class CorsConfig {
      @Bean
      public WebMvcConfigurer corsConfigurer() {
          return new WebMvcConfigurer() {
              @Override
              public void addCorsMappings(CorsRegistry registry) {
                  registry.addMapping("/api/**")
                      .allowedOrigins("http://localhost:5173")
                      .allowedMethods("GET", "POST", "PUT", "DELETE");
              }
          };
      }
  }
  ```
- [x] Create Axios instance with base URL configuration
- [x] Test basic API connection (fetch all releases)
- [ ] Set up MUI theme provider with custom colors

**Skills Learned:** Vite setup, npm/package management, project structure, CORS configuration

**Deliverable:** Running React app that successfully calls backend API

---

### Milestone 4.2: Core Layout & Navigation
**Status:** 🔲 Not Started  
**Goal:** Build app shell with navigation

**Tasks:**
- [ ] Create main App.jsx with Router setup
- [ ] Build responsive AppBar/Navbar component with MUI
    - [ ] Logo/title
    - [ ] Navigation links (Releases, Milestones, Gantt Chart)
    - [ ] Mobile-responsive drawer menu
- [ ] Create Dashboard/Home page layout
- [ ] Set up React Router routes:
    - [ ] `/` - Dashboard
    - [ ] `/releases` - All Releases
    - [ ] `/releases/:id` - Release Details
    - [ ] `/releases/new` - Create Release
    - [ ] `/releases/:id/edit` - Edit Release
    - [ ] `/milestones` - All Milestones
- [ ] Add MUI Container for consistent page layout
- [ ] Style with MUI theme colors

**Skills Learned:** React Router, MUI AppBar/Drawer, responsive design, component composition

**Deliverable:** Navigable app shell with working routes

---

### Milestone 4.3: Releases - Read & Display
**Status:** 🔲 Not Started  
**Goal:** Display releases from backend

**Tasks:**
- [ ] Create `releaseService.js` with API functions:
    - [ ] `getAllReleases()`
    - [ ] `getReleaseById(id)`
- [ ] Build `ReleasesPage` component
    - [ ] Fetch releases on component mount using `useEffect`
    - [ ] Handle loading state with MUI Skeleton or CircularProgress
    - [ ] Handle error state with MUI Alert
- [ ] Create `ReleaseCard` component using MUI Card
    - [ ] Display: releaseWindow, startDate, endDate
    - [ ] Add action buttons (View, Edit, Delete)
    - [ ] Use date-fns to format dates nicely
- [ ] Display releases in MUI Grid layout (responsive cards)
- [ ] Create `ReleaseDetailsPage` component
    - [ ] Fetch single release by ID from URL parameter
    - [ ] Display release info in MUI Paper/Card
    - [ ] Show placeholder for milestones (will add in next milestone)

**Skills Learned:** useState, useEffect, async/await, API calls, MUI Cards/Grid, loading states

**Deliverable:** View all releases and single release details

---

### Milestone 4.4: Releases - Create, Update, Delete
**Status:** 🔲 Not Started  
**Goal:** Full CRUD for releases

**Tasks:**
- [ ] Add API functions to `releaseService.js`:
    - [ ] `createRelease(releaseData)`
    - [ ] `updateRelease(id, releaseData)`
    - [ ] `deleteRelease(id)`
- [ ] Create `ReleaseForm` component (reusable for Create/Edit)
    - [ ] Use MUI TextField for releaseWindow
    - [ ] Use MUI DatePicker for startDate and endDate
    - [ ] Add form validation (required fields, date logic)
    - [ ] Handle form submission
    - [ ] Show success/error feedback with MUI Snackbar
- [ ] Build `CreateReleasePage`
    - [ ] Use ReleaseForm component
    - [ ] Navigate to releases list after successful creation
- [ ] Build `EditReleasePage`
    - [ ] Fetch existing release data
    - [ ] Pre-populate ReleaseForm with data
    - [ ] Update on submit
- [ ] Add delete functionality to ReleaseCard
    - [ ] Show MUI Dialog for confirmation
    - [ ] Call deleteRelease API
    - [ ] Remove from list after successful delete
    - [ ] Handle cascade delete of milestones

**Skills Learned:** Forms in React, controlled components, validation, MUI Dialog, useNavigate

**Deliverable:** Complete release management (Create, Read, Update, Delete)

---

### Milestone 4.5: Milestones - Full CRUD
**Status:** 🔲 Not Started  
**Goal:** Manage milestones within releases

**Tasks:**
- [ ] Create `milestoneService.js` with API functions:
    - [ ] `getAllMilestones()`
    - [ ] `getMilestonesByReleaseId(releaseId)`
    - [ ] `createMilestone(milestoneData)`
    - [ ] `updateMilestone(id, milestoneData)`
    - [ ] `deleteMilestone(id)`
- [ ] Update `ReleaseDetailsPage` to show milestones
    - [ ] Fetch milestones for the release
    - [ ] Display in MUI List or Timeline component
    - [ ] Add "New Milestone" button
- [ ] Create `MilestoneCard` component
    - [ ] Display: milestoneName, keyDate
    - [ ] Edit and Delete buttons
    - [ ] Calculate days until milestone
- [ ] Create `MilestoneForm` component
    - [ ] TextField for milestoneName
    - [ ] DatePicker for keyDate
    - [ ] Autocomplete dropdown to select release (for standalone create)
    - [ ] Validation
- [ ] Build `MilestonesPage` (view all milestones across releases)
    - [ ] Display in MUI DataGrid or custom table
    - [ ] Show release name with each milestone
    - [ ] Filter by release dropdown
- [ ] Add inline editing option for milestones
    - [ ] Click to edit milestone in place
    - [ ] Save/Cancel buttons

**Skills Learned:** Parent-child data relationships, MUI Autocomplete, inline editing, MUI DataGrid

**Deliverable:** Complete milestone management UI

---

### Milestone 4.6: Enhanced UX & Polish
**Status:** 🔲 Not Started  
**Goal:** Professional user experience

**Tasks:**
- [ ] Add search functionality to releases page
    - [ ] MUI TextField with search icon
    - [ ] Filter releases as user types
- [ ] Add sorting options (by date, name)
    - [ ] MUI Select dropdown or Table headers
- [ ] Improve error handling
    - [ ] Centralized error display with MUI Snackbar
    - [ ] User-friendly error messages
- [ ] Add empty states
    - [ ] "No releases yet" with icon and "Create" button
    - [ ] "No milestones" placeholder
- [ ] Loading states for all async operations
    - [ ] MUI LinearProgress at top of page
    - [ ] Skeleton loaders for content
- [ ] Success feedback for all actions
    - [ ] "Release created successfully!" toast
    - [ ] Green checkmark animations
- [ ] Confirm dialogs for destructive actions
    - [ ] MUI Dialog for delete operations
    - [ ] Warn about cascade deletes
- [ ] Make fully responsive
    - [ ] Mobile-friendly card layouts
    - [ ] Responsive navigation drawer
    - [ ] Touch-friendly buttons
- [ ] Add MUI theme customization
    - [ ] Custom color palette
    - [ ] Dark mode toggle (optional)

**Skills Learned:** UX best practices, MUI theming, responsive design, feedback patterns

**Deliverable:** Polished, professional-looking application

---

### Milestone 4.7: Gantt Chart Integration
**Status:** 🔲 Not Started  
**Goal:** Visual timeline view using Frappe Gantt

**Tasks:**
- [ ] Install Frappe Gantt: `npm install frappe-gantt`
- [ ] Create new route `/gantt` for Gantt chart page
- [ ] Create `GanttChartPage` component
- [ ] Format backend data for Frappe Gantt:
    - [ ] Transform releases into Gantt tasks
    - [ ] Transform milestones into task dependencies/markers
    - [ ] Example format:
      ```javascript
      {
        id: 'release-1',
        name: 'R25.1.0',
        start: '2025-10-01',
        end: '2025-10-31',
        progress: 50,
        dependencies: '',
        custom_class: 'release-bar'
      }
      ```
- [ ] Initialize Frappe Gantt with data
- [ ] Add view mode switcher (Day/Week/Month)
- [ ] Style Gantt bars with release colors
- [ ] Add milestone markers to timeline
- [ ] Make Gantt responsive (may require custom CSS)
- [ ] Integrate with Material-UI page layout
- [ ] Add tooltips showing release/milestone details on hover
- [ ] Optional: Make interactive (click to edit)

**Skills Learned:** Third-party library integration, data transformation, SVG/canvas manipulation

**Deliverable:** Interactive Gantt chart visualization

**Note:** Frappe Gantt and Material-UI work well together. Frappe Gantt is framework-agnostic and can be rendered inside MUI Paper/Card components.

---

## Phase 5: ~~Calendar & Gantt View~~
**Status:** ~~Replaced by Phase 4.7~~

_This phase has been integrated into Phase 4.7 since the entire frontend is now built with React._

---

## Phase 6: Security & User Management
**Focus: Authentication and authorization**

### Milestone 6.1: Spring Security Setup
**Status:** 🔲 Not Started

**Goal:** Add authentication

**Tasks:**
- [ ] Add Spring Security dependency
- [ ] Create SecurityConfig class with @EnableWebSecurity
- [ ] Configure HTTP security (which pages need auth)
- [ ] Create custom login page
- [ ] Test with in-memory users
- [ ] Protect admin routes
- [ ] Configure CORS to allow authenticated requests from React

**Skills Learned:** Spring Security basics, authentication, security configuration

**Deliverable:** Login system with basic authentication

---

### Milestone 6.2: User Roles
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

### Milestone 6.3: Registration & User Management
**Status:** 🔲 Not Started

**Goal:** Complete user management system

**Tasks:**
- [ ] Create admin page to manage users (list, create, edit, delete)
- [ ] Add user registration page (optional, based on needs)
- [ ] Implement password encoding with BCrypt
- [ ] Add password change functionality
- [ ] Create user profile page
- [ ] Add "created by" and "last modified by" audit fields
- [ ] Update React frontend with login/logout UI
- [ ] Store JWT tokens in React app
- [ ] Add protected routes in React Router

**Skills Learned:** Password security, BCrypt, user management patterns, JWT

**Deliverable:** Secured application with complete user management

---

## Phase 7: Notifications & Automation
**Focus: Slack integration and scheduled tasks**

### Milestone 7.1: Slack Webhook Setup
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

### Milestone 7.2: Notification Logic
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

### Milestone 7.3: Scheduled Notifications
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

### Milestone 7.4: Notification Management
**Status:** 🔲 Not Started

**Goal:** Configure notification preferences

**Tasks:**
- [ ] Create admin page for notification settings (React UI)
- [ ] Add enable/disable toggle per release
- [ ] Add notification preference fields (channels, timing, recipients)
- [ ] Create notification logs view with filtering
- [ ] Add retry logic for failed notifications
- [ ] Implement notification summary dashboard

**Skills Learned:** Configuration management, admin features, dashboard design

**Deliverable:** Fully configurable notification system

---

## Phase 8: Polish & Production Ready
**Focus: Deployment and refinement**

### Milestone 8.1: Switch to Production Database
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

### Milestone 8.2: Error Handling & Logging
**Status:** 🔲 Not Started

**Goal:** Production-ready error handling

**Tasks:**
- [ ] Create GlobalExceptionHandler with @ControllerAdvice
- [ ] Add custom exception classes
- [ ] Implement proper logging with SLF4J
- [ ] Create user-friendly error pages (404, 500) in React
- [ ] Log important events (user actions, errors, notifications)
- [ ] Configure log levels (dev vs production)

**Skills Learned:** @ControllerAdvice, logging best practices, exception handling

**Deliverable:** Robust error handling and logging

---

### Milestone 8.3: Testing
**Status:** 🔲 Not Started

**Goal:** Comprehensive test coverage

**Tasks:**
- [ ] Write unit tests for services using JUnit
- [ ] Write repository integration tests
- [ ] Write controller tests with MockMvc
- [ ] Add test coverage reporting
- [ ] Aim for 70%+ code coverage
- [ ] Write React component tests using React Testing Library
- [ ] Write E2E tests with Playwright or Cypress
- [ ] Document testing strategy

**Skills Learned:** JUnit, Mockito, @SpringBootTest, MockMvc, React Testing Library

**Deliverable:** Well-tested application

---

### Milestone 8.4: Deployment
**Status:** 🔲 Not Started

**Goal:** Deploy to production

**Tasks:**
- [ ] Package Spring Boot as executable JAR
- [ ] Build React for production: `npm run build`
- [ ] Choose deployment platform (Heroku, Railway, AWS, DigitalOcean, Vercel)
- [ ] Deploy backend application
- [ ] Deploy frontend application (or serve from Spring Boot)
- [ ] Configure environment variables
- [ ] Set up database on cloud provider
- [ ] Configure domain name (optional)
- [ ] Set up SSL certificate
- [ ] Monitor application logs

**Skills Learned:** Deployment, cloud platforms, environment configuration, CI/CD

**Deliverable:** Live production application

---

## Progress Tracking

| Phase | Status | Start Date | Completion Date |
|-------|--------|------------|-----------------|
| Phase 1 | ✅ Complete | 2025-10-01 | 2025-10-04 |
| Phase 2 | ✅ Complete | 2025-10-04 | 2025-10-05 |
| Phase 3 | ✅ Complete | 2025-10-05 | 2025-10-07 |
| Phase 4 (React) | 🔲 Not Started | | |
| Phase 5 | ~~Integrated into 4.7~~ | | |
| Phase 6 | 🔲 Not Started | | |
| Phase 7 | 🔲 Not Started | | |
| Phase 8 | 🔲 Not Started | | |

**Status Legend:**
- 🔲 Not Started
- 🚧 In Progress
- ✅ Complete

## Learning Resources

### Phase 1-2: Java Basics
- MOOC.FI Java Programming course (mooc.fi)
- Practice coding exercises daily

### Phase 3: Spring Boot & JPA
- Spring Boot official guides (spring.io/guides)
- Baeldung Spring tutorials (baeldung.com)
- Spring Boot Tutorial by Amigoscode (YouTube)

### Phase 4: React & Frontend
**React Fundamentals:**
- React Official Tutorial (react.dev/learn) - **Start here!**
- "React in 100 Seconds" by Fireship (YouTube)
- freeCodeCamp React Course (YouTube)

**Material-UI:**
- MUI Official Documentation (mui.com)
- MUI Templates for inspiration
- Search: "MUI React CRUD example"

**Frappe Gantt:**
- GitHub Documentation (github.com/frappe/gantt)
- CodePen examples for integration patterns

**General:**
- MDN Web Docs (HTML/CSS/JavaScript)
- JavaScript.info for modern JS concepts

### Phase 6: Security
- Spring Security official docs
- Baeldung Spring Security tutorials
- JWT authentication guides

### Phase 7-8: Advanced Topics
- Spring Scheduling documentation
- Slack API documentation (api.slack.com)
- Testing with Spring Boot guides
- React Testing Library docs
- Deployment platform documentation

---
