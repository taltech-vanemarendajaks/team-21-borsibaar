# Team Information

## Team Name

// TODO: come up with team name

## Team Members

| Name      | GitHub Username   |
|-----------|-------------------|
| Tom       | @tomkabel         |
| Hanna     | @hrauds           |
| Kevin     | @Kevindaman       |
| Tener     | @tener-ilistom    |

## Our Workflow

We will follow a feature-branch workflow with mandatory pull request reviews to ensure code quality and collaboration.

### 1. The main Branch

- The main branch is our primary branch and represents the stable, production-ready version of our project.
- **Direct pushes to the main branch are disabled.** All changes must go through a Pull Request.

### 2. Creating Branches (Local Development)

- All new work (features, bug fixes, documentation) must be done on a separate branch.
- Before creating a new branch, always pull the latest changes from the remote main branch to ensure you are up to date: git pull origin main.
- Branch names should be descriptive and use a prefix like feature/, fix/, or docs/.
  - Example: feature/user-authentication
  - Example: fix/navbar-alignment

### 3. Code Reviews via Pull Requests (PRs)

- When a feature is complete, the developer will push their branch to the remote repository and open a Pull Request (PR) on GitHub, targeting the main branch.
- The PR description must clearly explain the purpose of the changes.
- At least **one other team member** must review the PR, provide feedback, and approve the changes. No one can merge their own PR.

### 4. Merging

- Once a PR has been reviewed and approved, it can be merged into the main branch.
- We will use the **"Squash and Merge"** option on GitHub. This keeps our main branch history clean with a single, comprehensive commit for each feature.
- After a successful merge, the feature branch should be deleted from the remote repository to keep our workspace tidy.
