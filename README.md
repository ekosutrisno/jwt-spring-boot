### Implementasi JWT Sederhana

- Stack
  - SpringBoot
  - By: Eko Sutrisno  
    
- Run App
  - Clone Repo
  - Buka di IDE STS/VS Code/Intellij Idea
  - Nanti akan otomatis downlod semua dependency
  - Setelah selesai, Run Application
    
- Data (Request & Response)
  - Generate Token
    - Method ``POST``
    - Path ``/authenticate``
    - Header ``Content-Type: application/json``
    - Body
      ````shell 
      {
        "username": "ekosutrisno",
        "password": "password"
      }
      ````
    - Response ``200``
       ````shell
      {
        "username": "ekosutrisno",
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJla29zdXRyaXNubyIsImV4cCI6MTYxNDc2MzM1MCwiaWF0IjoxNjE0NzQ1MzUwfQ.cqfQ3QkZ3odbA0VL4Lxm0VjRudy9r4ReYxH5eqv3QMrHYBBp82pMZQMIKlOXZFQCdI-MNpCRAkERI3tYMWBv5A"
      }
       ````
  - Test Request
    - Method ``GET``
    - Path ``/all``
    - Header ``Authorization: {{token}}``
    - Response ``200``
       ````shell
        [
          {
          "id": Long,
          "kodeAgama": String,
          "deskripsi": String
          },
        ]
       ````
    - Response Jika Token InValid ``401``
       ````shell
        {
          "timestamp": "2021-03-03T04:43:25.075+0000",
          "status": 401,
          "error": "Unauthorized",
          "message": "Unauthorized",
          "path": "/all"
        }
       ````