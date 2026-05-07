# MathUtils-CI

Maven project for Week 9 – Bai 3 (CI/CD with GitHub Actions).

## Quick start

```bash
mvn clean package       # compile + test + package JAR
mvn test                # run unit tests only
```

## CI/CD Workflows

| File | Trigger | Purpose |
|------|---------|---------|
| `.github/workflows/ci.yml` | push / pull_request | Main CI pipeline |
| `.github/workflows/ci-fail-demo.yml` | Manual (`workflow_dispatch`) | Failure debug demo |

## Important: Repository setup

For GitHub Actions to work, the **repository root** must contain
the `.github/` folder. Either:

**Option A** – Use `Bai9_3/` as the repo root:
```
git init
git remote add origin https://github.com/<user>/<repo>.git
git add .
git commit -m "feat: add CI/CD workflow"
git push -u origin main
```

**Option B** – Use `NinhDucHai_25021747_Week9/` as the repo root.
The workflows already use `working-directory: Bai9_3` and
`path: Bai9_3/target/...` so they work from either level.

## Artifact

After a successful build, GitHub Actions uploads:
- **MathUtils-CI-jar**: `target/MathUtils-CI.jar` (kept 30 days)
- **surefire-reports**: XML test reports (kept 7 days, always)
