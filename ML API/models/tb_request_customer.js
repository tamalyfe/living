const dbpool = require('../config/database')

const getAllRequest = () => {
  const SQLQuery = 'SELECT * FROM tb_request_customers'

  return dbpool.execute(SQLQuery);
}

const getRequestbyIdentifier = (identifier_request_customer) => {
  const SQLQuery = `SELECT * FROM tb_request_customers WHERE identifier_request_customer = '${identifier_request_customer}'`;

  return dbpool.execute(SQLQuery);
}


const checkRequestExistence = async (identifier_request_customer) => {
  const SQLQuery = 'SELECT CASE WHEN COUNT(*) > 0 THEN "yes" ELSE "no" END AS result FROM tb_request_customers WHERE identifier_request_customer = ?';
  const [results] = await dbpool.execute(SQLQuery, [identifier_request_customer]);
  return results[0].result;
};

const createNewRequest = (body) => {
  const SQLQuery = `INSERT INTO tb_request_customers 
                    (identifier_request_customer, name_customer, contact_customer, domicile_customer, description_request_customer, location_project, price_list_project_cash, price_list_project_credit, status_project) 
                    VALUES (
                      '${body.identifier_request_customer}',
                      '${body.name_customer}',
                      '${body.contact_customer}',
                      '${body.domicile_customer}',
                      '${body.description_request_customer}',
                      '${body.location_project}',
                      '${body.price_list_project_cash}',
                      '${body.price_list_project_credit}',
                      '${body.status_project}'
                    )`;
  return dbpool.execute(SQLQuery);
}

const UpdateRequest = (body, identifier_request_customer) => {
  const SQLQuery = `UPDATE tb_request_customers 
                    SET 
                      name_customer = '${body.name_customer}',
                      contact_customer = '${body.contact_customer}',
                      domicile_customer = '${body.domicile_customer}',
                      description_request_customer = '${body.description_request_customer}',
                      location_project = '${body.location_project}',
                      price_list_project_cash = '${body.price_list_project_cash}',
                      price_list_project_credit = '${body.price_list_project_credit}',
                      status_project = '${body.status_project}'
                    WHERE identifier_request_customer = '${identifier_request_customer}'`;

  return dbpool.execute(SQLQuery);
};


const DeleteRequest = (identifier_request_customer) => {
  const SQLQuery = `DELETE FROM tb_request_customers WHERE identifier_request_customer = '${identifier_request_customer}'`;
  return dbpool.execute(SQLQuery);
}

module.exports = {
  getAllRequest,
  createNewRequest,
  UpdateRequest,
  DeleteRequest,
  getRequestbyIdentifier,
  checkRequestExistence
}