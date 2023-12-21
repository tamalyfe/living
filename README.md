# Living API || Capstone Bangkit 2023 Batch 2

As for the main API for MD we build a RESTFUL API using Node.js and Express as the framework, whil for Machine Learning model was build using Flask. Both API is able to integrate with cloud MySQL that already deployed.

## Getting Started for API (MD)

to start running this API locally, first
create a database named living then import the database included in the database tab, make sure all tables and data is ready.
then,
```bash
git clone 
```
go into the folder after that run
```bash
npm install
```
Copy the example env file and rename it as env and adjust it to the database that just being made either on local MySQL of cloud MySQL
```bash
cp .env.example .env
```
in the folder terminal, Start the server
```bash
npm start
```
the server should run at
```bash
localhost:8080
```

as for the routes that can be used for testing it on postman
there are 

### Projects
```bash
/projects
```
(for GET all projects)

(POST or create a project, the post body should look like below)
```bash
{
            "name_project": " ",
            "type_project": " ",
            "ready": "FALSE",
            "indent": "TRUE",
            "location_project": " ",
            "price_list_project_cash": 345000000,
            "price_list_project_credit": 465000000,
            "promo": 65000000,
            "description_project": " ",
            "bedroom": 2,
            "bathroom": 1,
            "carport": 1,
            "building_area": 36,
            "surface_area": 60,
            "facility": " ",
            "name_developer": "PT. MALAIKA SANTARA ABADI",
            "contact_developer": " ",
            "loan_bank": " ",
            "handover": 3
}
```
```bash
/projects/:identifier_project
```
(for GET project by ID)

(for DELETE project by ID)

(for UPDATE/PATCH project, the body can be filled with any data that want to be updated)


### recruitments
```bash
/recruitments
```
(for GET all recruitments)

(POST or create a recruitments, the post body should look like below)
```bash
{
            
            "identifier_recruitment_team": 64,
            "name_team": " ",
            "post_team": " ",
            "domicile_team": " ",
            "job_description": " ",
            "experience": " ",
            "certificate": " "
}
```
```bash
/recruitments/:identifier_recruitment_team
```
(for GET recruitments by ID)

(for DELETE recruitments by ID)

(for UPDATE/PATCH recruitments, the body can be filled with any data that want to be updated)


### requests
```bash
/requests
```
(for GET all requests)

(POST or create a requests, the post body should look like below)
```bash
{
            "identifier_request_customer": 126,
            "name_customer": " ",
            "contact_customer": " ",
            "domicile_customer": " ",
            "description_request_customer": " ",
            "location_project": " ",
            "price_list_project_cash": 5000,
            "price_list_project_credit": 300,
            "status_project": " "
}
```
```bash
/requests/:identifier_request
```
(for GET requests by ID)

(for DELETE requests by ID)

(for UPDATE/PATCH requests, the body can be filled with any data that want to be updated)




## Getting Started for API (ML)

to start running this API locally, first
```bash
git clone 
```
go into the folder after that run
```bash
pip install -r requirements.txt
```
Copy the example env file and rename it as env and adjust it to the database that just being made either on local MySQL of cloud MySQL
```bash
cp .env.example .env
```
in the folder terminal, Start the server
```bash
python main.py
```
the server should run at
```bash
localhost:5000 or http://127.0.0.1:5000
```

as for the routes that can be used for testing it on postman is
```bash
/search 
```
for POST with a body
```bash
{
  "location": "Babelan",
  "price": 300000000,
  "total_sqft": 34,
  "surface_area": 50
}
```

the output will be the data of 3 data of projects that is the result of filtering using the models

