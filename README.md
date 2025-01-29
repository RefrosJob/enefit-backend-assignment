# enefit-backend-assignment

### Backend assignment for enefit by Robert

## Description

I'll just give a short summary here, outlining what I did and did not do and why.

DID:

- Meet the MVP requirements
- Do client caching for outer API I needed to request in this assignment
- Very simplistic logging using provided by Spring boot logger. It's stinky, but it does the job at showing what was
  requested and then, that would absolutely not do for production tho
- Custom exceptions that are converted to proper HTTP responses... most of the time
- Migrations to automatically set up the database with needed tables and values, runs on startup
- Have to note that Unit tests I did are very basic. Had little time left when got to them.

NOT DID:

- Down Migrations. Me running out of time will be a common theme here, since this week was a rollercoaster of
  emergencies and I had very little time in refining the solution. I usually take time in things I don't know much to
  learn more in-depth and use it better later. This approach let me down significantly this time.
- Tokenized Authentication and password hashing. Yep, in this here solution passwords are stored stripped naked (reddit
  style) and user (customer) UUID's in place of the Auth tokens. I put it away as it is very time-consuming, given that
  holding
  hash salts and managing token sessions requires at least one extra database table and a decent bunch of logic behind
  them.
- Really wanted to do consumer caching too, since not only market data we request is often unmutable, consumption data
  also is, and can be cached pretty safely. Yet I spent too much time trial and erroring through this, and ended up with
  nothing, as it was constantly failing on me, so I cut it out and vowed to try it again later.
- Database change management. Yeah, I did a bunch of gitops for one other project before, including full deployment
  pipeline to hosting service. But this thing breaks my mind a bit, I'll definetely take a look at it and try it in my
  spare time, but not here, for sure.

## Usage

I used IntelliJ Idea Ultimate's devcontainer setup, so you could navigate to <code>
.devcontainer/devcontainer.json </code> and run it from there using <code>Create container and mount...</code> option.

Otherwise, this container should be openable through <code>docker-compose</code>.

Container itself already has a postgres installation together with a simple pg-admin setup.

Within the container Gradle can be used to build and start the Application with
<code>java/enefit_backend_assignment/EnefitBackendAssignmentApplication.java</code> root reference.

The Application *SHOULD* default to forwarding port 8080 through the devcontainer, but in case it does and you can't
connect you might also work on Debian/Ubuntu based Linux repo and you have <code>ufw</code> enabled.

You can turn it off by writing <code>sudo ufw disable</code> but I would suggest looking up the manual and allowing
necessary TCP ports (8080, 5432, 15432) by hand.

On another note, in intellij Port forwarding within the container networks has been less than stellar for me, and it
required me to manually set it up in "Services" tab so that dev container and pgsql could communicate between each
other.
At some point it just started to work consistently, and frankly rn I have no time to verify it on other systems.