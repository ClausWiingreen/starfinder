## **0.1.0**&emsp;<sub><sup>2025-08-04 ([ed51e7d...fc55954](https://github.com/ClausWiingreen/starfinder/compare/ed51e7d093f6925ce4e285a4c668487f833096e2...fc559540afb9d3d1788944a4aa6776e94442bf24?diff=split))</sup></sub>

### Features

- initialize Spring Boot project with Maven wrapper ([ed51e7d](https://github.com/ClausWiingreen/starfinder/commit/ed51e7d093f6925ce4e285a4c668487f833096e2))
- add CharacterController with POST endpoint for character creation ([9181dfc](https://github.com/ClausWiingreen/starfinder/commit/9181dfc92e41d9f0d0c39d03c5be408787a77e55))
- add Character entity with UUID id ([61b84c1](https://github.com/ClausWiingreen/starfinder/commit/61b84c1fa21e788557f033a2c9c5c28d6af22ff1))
- add CharacterRepository for persistence ([fe5a93f](https://github.com/ClausWiingreen/starfinder/commit/fe5a93fc8425703a7d5b4c705813f11e277db803))
- persist new Character entity via CharacterRepository ([07f71c2](https://github.com/ClausWiingreen/starfinder/commit/07f71c2eafc89668859f78b0a016e85589d85379))
- allow setting character name via controller ([58661e3](https://github.com/ClausWiingreen/starfinder/commit/58661e31faa7620f0f47119a05f2311adc55ec23))
- associate Character entity with User ([64b2330](https://github.com/ClausWiingreen/starfinder/commit/64b2330c7218269c6fe466972d46c5c5db7a6952))
- add support for looking up current authenticated user ([e137c0e](https://github.com/ClausWiingreen/starfinder/commit/e137c0e7f39a82344ad095af9dd2af30f839b904))
- store owner when creating character ([8ef5cac](https://github.com/ClausWiingreen/starfinder/commit/8ef5cac5ee99c2a0915b50c560be70f67a05c17f))
- add character update endpoint to CharacterController ([251eeb6](https://github.com/ClausWiingreen/starfinder/commit/251eeb6948acbcc8a93754d8f8579e86cb9e53d5))
- update character name via POST request ([7152253](https://github.com/ClausWiingreen/starfinder/commit/71522536c2d1df766a56c7a1f46c740b45c72209))
- add basic Spring Security configuration ([55b0dee](https://github.com/ClausWiingreen/starfinder/commit/55b0dee80c1c6aac2814b6657ea6c95aebc63f25))
- enforce user authentication on character update ([8de9f57](https://github.com/ClausWiingreen/starfinder/commit/8de9f571f7ec03ed4a29ddb42c3402238ac8b922))
- restrict character updates to their owners ([0e5b5a2](https://github.com/ClausWiingreen/starfinder/commit/0e5b5a23b64144e1d866d6300d79fadd42e33716))
- add request validation for character creation ([14e8389](https://github.com/ClausWiingreen/starfinder/commit/14e83898e07ec7f2c66126366660e3df79953ae2))
- add validation to character update endpoint ([cec6c46](https://github.com/ClausWiingreen/starfinder/commit/cec6c4684944f6f441ede49e37ea106972603430))
- add endpoint for character overview page ([a8e8d76](https://github.com/ClausWiingreen/starfinder/commit/a8e8d7678c7c37d997964760a67d7de1abcd5860))
- populate model with characters in overview endpoint ([2b172c6](https://github.com/ClausWiingreen/starfinder/commit/2b172c6c5bc6a2ee0d80d94646f000b32446d5b9))
- restrict character overview to current user ([e4b7532](https://github.com/ClausWiingreen/starfinder/commit/e4b7532a48937e09e1dfc803d54f9eb0acca4624))
- add character deletion endpoint to controller ([c3af6e0](https://github.com/ClausWiingreen/starfinder/commit/c3af6e0fd5688d5231f4045c1b095a6497699124))
- implement character deletion logic in controller ([efc3e1e](https://github.com/ClausWiingreen/starfinder/commit/efc3e1eb66b452fd5a3bf0855bb0b2a962c37bb9))
- replaces user manegement with custom solution ([f434070](https://github.com/ClausWiingreen/starfinder/commit/f434070640e3344a0e404bde1e2d86498b2597b7))
- creates head fragment for reuse between pages ([caaa511](https://github.com/ClausWiingreen/starfinder/commit/caaa511b2ecfd2c231699fcff21745972e2780e6))
- creates a navigation bar ([9009602](https://github.com/ClausWiingreen/starfinder/commit/900960228b481a1368674352f751ea052229bc7e))
- creates generic layout to be used across the site ([34821dd](https://github.com/ClausWiingreen/starfinder/commit/34821dd839efe4867991caa305a5c0bff89a6c20))
- adds the error and index page as they are more or less static ([9e9a7cd](https://github.com/ClausWiingreen/starfinder/commit/9e9a7cd4fbbd950f9397cebe2f42e464091d7087))
- adds overview page for characters ([abfb1f8](https://github.com/ClausWiingreen/starfinder/commit/abfb1f825496f173a17dcbf01e648bf241c29181))
- adds edit and create page as they are almost the same ([dcbc60a](https://github.com/ClausWiingreen/starfinder/commit/dcbc60a25a0b2d4a73feb517a7fca99aaf805202))
- makes navbar heading clickable ([3fc91e8](https://github.com/ClausWiingreen/starfinder/commit/3fc91e88908d9916149e24e5034ae539ddf393cb))
- introduce button component styles ([5d878dd](https://github.com/ClausWiingreen/starfinder/commit/5d878dde0b44dce2f0604501715334f719d1768b))
- add card hover variant for consistent interaction design ([1fd0c61](https://github.com/ClausWiingreen/starfinder/commit/1fd0c61d06d67dffbdaa8e1812a5f8b9f32440d8))
- add reusable header and input field classes ([ae5a663](https://github.com/ClausWiingreen/starfinder/commit/ae5a663bbd68a3fbd8a27162232fd1088b68e472))
- implement user registration with password encryption and integration tests ([074918d](https://github.com/ClausWiingreen/starfinder/commit/074918dba923d4cb494f733745457e2dd876964b))
- prevent registration if username already exists and return to index page ([5dc4202](https://github.com/ClausWiingreen/starfinder/commit/5dc4202686110aa05d80d10fb0727e22372d65a2))
- introduce RegistrationForm record and use BindingResult to handle validation errors during registration ([2e86e8d](https://github.com/ClausWiingreen/starfinder/commit/2e86e8d1e8102d3eee87cf928cb961b2bc67ad73))
- display current user in navbar using controller advice and enhance header layout styling ([3ce2ed0](https://github.com/ClausWiingreen/starfinder/commit/3ce2ed02c131af300f7f55ad618f3babcf8e20e9))
- validate password is not blank during registration and reject invalid submissions with appropriate error ([7b02c40](https://github.com/ClausWiingreen/starfinder/commit/7b02c40027504d5724b0b0f329b3f68d7b334add))
- add validation to ensure username is not blank during registration ([567259f](https://github.com/ClausWiingreen/starfinder/commit/567259fd787494a684d050f78249ac22eddcc039))
- enforce minimum password length requirement during registration ([45bf9bb](https://github.com/ClausWiingreen/starfinder/commit/45bf9bb5c04016ece4f6943941867f4242bfa4ae))

##### &ensp;`user`

- add User entity and repository ([fe93150](https://github.com/ClausWiingreen/starfinder/commit/fe93150ecdc5115709cbd565b27847316ff6c03c))

### Bug Fixes

- restrict character deletion to character owner only ([e0e779d](https://github.com/ClausWiingreen/starfinder/commit/e0e779dbf533f444734eb360c428345a89ef9e0d))
- reverts security configuration as it caused too many redirects ([ce9993b](https://github.com/ClausWiingreen/starfinder/commit/ce9993b080693e2b66181d810d8cfea42f1accad))
- makes mvnw executable ([c8e8ffa](https://github.com/ClausWiingreen/starfinder/commit/c8e8ffa6f44d0b19b7c25d4b22227c254ec6c7db))

<br>

## **0.1.0**&emsp;<sub><sup>2025-07-29 (ed51e7d093f6925ce4e285a4c668487f833096e2...31ffbe8462061e4fd9267afc3ec5060179becd8d)</sup></sub>

### Features

- initialize Spring Boot project with Maven wrapper (ed51e7d093f6925ce4e285a4c668487f833096e2)
- add CharacterController with POST endpoint for character creation (9181dfc92e41d9f0d0c39d03c5be408787a77e55)
- add Character entity with UUID id (61b84c1fa21e788557f033a2c9c5c28d6af22ff1)
- add CharacterRepository for persistence (fe5a93fc8425703a7d5b4c705813f11e277db803)
- persist new Character entity via CharacterRepository (07f71c2eafc89668859f78b0a016e85589d85379)
- allow setting character name via controller (58661e31faa7620f0f47119a05f2311adc55ec23)
- associate Character entity with User (64b2330c7218269c6fe466972d46c5c5db7a6952)
- add support for looking up current authenticated user (e137c0e7f39a82344ad095af9dd2af30f839b904)
- store owner when creating character (8ef5cac5ee99c2a0915b50c560be70f67a05c17f)
- add character update endpoint to CharacterController (251eeb6948acbcc8a93754d8f8579e86cb9e53d5)
- update character name via POST request (71522536c2d1df766a56c7a1f46c740b45c72209)
- add basic Spring Security configuration (55b0dee80c1c6aac2814b6657ea6c95aebc63f25)
- enforce user authentication on character update (8de9f571f7ec03ed4a29ddb42c3402238ac8b922)
- restrict character updates to their owners (0e5b5a23b64144e1d866d6300d79fadd42e33716)
- add request validation for character creation (14e83898e07ec7f2c66126366660e3df79953ae2)
- add validation to character update endpoint (cec6c4684944f6f441ede49e37ea106972603430)
- add endpoint for character overview page (a8e8d7678c7c37d997964760a67d7de1abcd5860)
- populate model with characters in overview endpoint (2b172c6c5bc6a2ee0d80d94646f000b32446d5b9)
- restrict character overview to current user (e4b7532a48937e09e1dfc803d54f9eb0acca4624)
- add character deletion endpoint to controller (c3af6e0fd5688d5231f4045c1b095a6497699124)
- implement character deletion logic in controller (efc3e1eb66b452fd5a3bf0855bb0b2a962c37bb9)

##### &ensp;`user`

- add User entity and repository (fe93150ecdc5115709cbd565b27847316ff6c03c)

### Bug Fixes

- restrict character deletion to character owner only (e0e779dbf533f444734eb360c428345a89ef9e0d)

<br>

