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

