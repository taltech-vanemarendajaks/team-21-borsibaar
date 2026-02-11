# Börsibaar (Team 21)

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.5-green) ![Next.js](https://img.shields.io/badge/Next.js-15-black) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)

## What is Börsibaar?

Picture this: you're at a bar where drink prices act like they're trading on Wall Street. The moment that IPA starts flying off the shelves, the price ticks up. Nobody's touching that house red? Watch it drop until someone bites. That's Börsibaar in a nutshell, a fullstack web app that turns pricing into a living, breathing game.

Bartenders tap orders into tablets. Prices shift automatically based on what's flying out the door. Customers stare at a screen that looks suspiciously like a stock ticker, watching the numbers dance. The whole system breathes and adapts, never sitting still.

Dynamic pricing drives urgency. The ticker builds anticipation. The POS keeps bartenders fast and focused. And somewhere in the back, inventory tracks itself so the business doesn't run dry on a Friday night.

## How It's Built

The backend runs Spring Boot 3.5.5 with Java 21, PostgreSQL handling the data, and Spring Security wrapping everything in OAuth2 and JWT authentication. It's solid, tested, and doesn't get in your way.

The frontend lives in Next.js 15 with the App Router, TypeScript keeping things honest, Tailwind CSS v4 for styling that actually looks good, and Shadcn UI providing components that feel polished right out of the box.

Docker handles local development so you don't have to juggle database installs on your machine. One command, everything spins up, you get to work.

### Backend Architecture

The Spring Boot backend follows a clean layered pattern. Controllers catch API requests and pass them along. Services hold the business logic where the real decisions happen. Repositories talk to the database through Spring Data JPA. Entities map directly to tables, DTOs shuttle data around, and MapStruct handles the boring conversions automatically.

The config folder houses Spring's configuration classes. Exceptions get caught and handled properly instead of bubbling up as cryptic stack traces. Jobs run in the background, like the PriceCorrectionJob that keeps pricing honest. Utilities hold the little helpers that pop up everywhere.

Security relies on OAuth2 through Google for login, JWT tokens keeping sessions alive, Liquibase managing database migrations without breaking things, Lombok cutting the boilerplate noise, and Spring Validation punching back when bad data shows up.

### Frontend Structure

Next.js 15 uses the App Router to structure pages. The landing page sits at app/page.tsx. The protected section houses the dashboard, inventory, and POS under the sidebar layout. Login, onboarding, and the public client view each get their own space.

API routes in app/api connect to the backend. UI components in components/ui come from Shadcn and Radix, giving you accessible primitives that just work. Global styles live in app/globals.css with Tailwind doing the heavy lifting. TypeScript watches your back with strict mode enabled, catching mistakes before they reach production.

## What You Need to Run It

You'll need Java JDK 21 installed. Node.js should be at version 20 or newer. Docker and Docker Compose handle the infrastructure piece so you don't have to think about PostgreSQL installation.

## Getting Started

### Set Up Your Environment

Copy the sample environment file first. The .sample.env in the root directory becomes your .env file. Fill in the Google OAuth credentials, otherwise login fails and nobody gets in.

### Start the Database

```
docker compose up -d
```

That single command spins up PostgreSQL and gets it ready for connections. The -d flag runs it in the background so your terminal stays clean.

### Launch the Backend

```
cd backend
./mvnw spring-boot:run
```

The backend fires up, connects to the database, and starts listening on port 8080. Maven downloads dependencies on first run, so grab a coffee if you're starting from scratch.

Building the backend happens with `./mvnw clean package`. Tests run via `./mvnw test`. Skip tests entirely with `-Dspring-boot.run.skipTests=true` when you want to iterate fast.

Once it's running, Swagger documentation lives at `http://localhost:8080/swagger-ui.html`. Bookmark that thing, you'll reference it constantly.

### Launch the Frontend

```
cd frontend
npm install
npm run dev
```

npm install pulls in every dependency. npm dev starts the Next.js development server. The app appears at `http://localhost:3000` with hot reload, so changes show up instantly.

Building for production uses `npm run build`. Starting the production server needs `npm start`. Linting catches style issues with `npm run lint`.

### Docker Usage

```
docker compose up
```

Running docker compose without flags starts the full development environment. Both the database and backend containers spin up together, talking to each other through Docker's internal network. Stop everything with Ctrl+C or `docker compose down` when you're done.

## Configuration

<details>
<summary>Sample .env file</summary>

```env
POSTGRES_DB=borsibaar
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

# JDBC URL
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/borsibaar
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres

# OAuth2 (Required for Auth)
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret

# Security
JWT_SECRET="use_openssl_rand_base64_32_here"
```

</details>

<details>
<summary>application.properties example</summary>

```properties
spring.application.name=Borsibaar

spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}

spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}
spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}
spring.security.oauth2.client.registration.google.scope=openid,profile,email
spring.security.oauth2.client.registration.google.redirect-uri={baseUrl}/login/oauth2/code/{registrationId}
spring.security.oauth2.client.registration.google.client-name=Google

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.open-in-view=false

spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml
spring.liquibase.enabled=true
spring.sql.init.mode=never

jwt.secret=${JWT_SECRET}
app.cors.allowed-origins=http://localhost:3000,http://127.0.0.1:3000
app.frontend.url=http://localhost:3000

server.forward-headers-strategy=framework
```

</details>

## Project Layout

### Backend

The backend folder contains the entire Java application, organized into clear layers. Controllers handle incoming API requests and nothing more. Services contain the business logic where pricing rules, inventory checks, and organizational boundaries live. Repositories use Spring Data JPA to query PostgreSQL without writing raw SQL everywhere. Entities define the database schema directly in code, making migrations manageable.

Mappers leverage MapStruct to convert between entities and DTOs without the boilerplate. DTOs carry request and response data, keeping the internal model separate from what the API exposes. Configuration classes set up Spring Security, CORS, and application-wide settings. Exception handlers catch errors and return consistent responses instead of letting Spring's defaults bubble up.

Background jobs run scheduled tasks, the PriceCorrectionJob being a prime example. It nudges prices back toward sanity when the algorithm gets too aggressive. Utility classes hold the little helpers that every layer needs, things like date formatting or validation helpers.

### Frontend

The frontend folder contains the Next.js application. The protected routes live under app/(protected), wrapped in a sidebar layout that persists across pages. The dashboard gives the main overview, inventory handles product management, and the POS serves as the point of sale interface.

The public-facing client view sits in app/client, displaying the price ticker for customers. Login handles authentication through OAuth2. Onboarding walks new users through initial setup. API routes in app/api provide backend integration points.

UI components in components/ui come from Shadcn and Radix, giving you accessible primitives that look good and work correctly. Global styles in app/globals.css use Tailwind CSS v4 for rapid styling. The entire frontend uses TypeScript with strict mode enabled, catching type errors before they become runtime bugs.

## Current Status

Börsibaar works. The core features ship, the app doesn't crash during normal use, and people can actually use it to run a bar. But development continues. We're adding features, fixing edge cases, and cleaning up the rough edges that show up when real users get their hands on it.

## Known Issues and Plans

We're honest about what's broken. Here's what we're looking at.

### Backend

Inventory and pricing need consistency. Several services fetch related entities manually through repositories instead of navigating object graphs, creating N+1 query risks. The InventoryService.getByOrganization method, for example, loops through inventory items and calls productRepository.findById for each one, hitting the database over and over instead of loading relationships once.

The dynamic pricing logic exists and functions, but it scatters across PriceCorrectionJob and InventoryService rather than living in a dedicated domain service where it belongs. Clean it up, put it in one place, and testing becomes easier.

Inventory stores both product_id and organization_id while Product also carries organization_id. This duplication adds complexity and confuses the data model. Either make Inventory depend purely on Product, or document why the redundancy exists through constraints and invariants.

Validation on write paths needs work. Many DTOs accept bad data without pushing back. Negative prices, impossible quantity changes, min prices exceeding max prices, none of these get caught consistently. The database constraints catch some issues, but the application layer should handle business rules before data ever reaches the database.

Cross-cutting concerns show inconsistencies. Controllers handle errors differently, some returning custom responses while others let Spring's defaults leak through. Response shapes vary between endpoints. Tenant isolation and authorization checks repeat in every method instead of living in a single place where they get applied automatically.

Tests cover happy paths adequately but fall short on edge cases. Concurrent updates, cross-organization access, deleted products, inactive products, none of these scenarios get proper coverage. Logging remains technical instead of capturing domain events that would help debug production issues.

### Frontend

Some pages grew too large. The inventory page mixes data fetching, business rules, and UI rendering all in one file. Break it down into smaller components that each do one thing well.

TypeScript types get written by hand and drift out of sync with what the backend actually returns. A shared contract layer, or generating types directly from OpenAPI documentation, would keep everything aligned and reduce runtime errors.

POS flows couple tightly to React component state. Station selection, product loading, cart building, and sale submission all live inside UI components. Pulling this logic into custom hooks or service modules would make it testable and reusable.

The client-facing pricing view handles organization identification implicitly through URLs and query parameters instead of making the contract explicit and obvious. Refactor the routing to make organization selection clear and intentional.

Error handling varies between pages. Some catch failures gracefully, others let errors bubble up or show cryptic messages. A centralized helper distinguishing "not logged in" from domain errors would provide consistent UX everywhere.

## How to Contribute

Fork the repository first. Create a feature branch with a descriptive name. Make your changes, write clear commit messages, and push to your branch. Open a pull request and explain what you're changing and why.

Scope is limited to README.md updates. Only modify this file, nothing else in the codebase.
