const dbpool = require('../config/database')

const getAllProject = () => {
  const SQLQuery = 'SELECT * FROM tb_projects'

  return dbpool.execute(SQLQuery);
}

const getProjectbyIdentifier = (identifier_project) => {
  const SQLQuery = `SELECT * FROM tb_projects WHERE identifier_project = '${identifier_project}'`;

  return dbpool.execute(SQLQuery);
}


const checkProjectExistence = async (identifier_project) => {
  const SQLQuery = 'SELECT CASE WHEN COUNT(*) > 0 THEN "yes" ELSE "no" END AS result FROM tb_projects WHERE identifier_project = ?';
  const [results] = await dbpool.execute(SQLQuery, [identifier_project]);
  return results[0].result;
};

const createNewProject = (body) => {
  const SQLQuery = `INSERT INTO tb_projects 
                    (identifier_project, name_project, type_project, ready, indent, location_project, price_list_project_cash, price_list_project_credit, promo, description_project, bedroom, bathroom, carport, building_area, surface_area, facility, name_developer, contact_developer, loan_bank, handover) 
                    VALUES (
                      '${body.identifier_project}',
                      '${body.name_project}',
                      '${body.type_project}',
                      '${body.ready}',
                      '${body.indent}',
                      '${body.location_project}',
                      '${body.price_list_project_cash}',
                      '${body.price_list_project_credit}',
                      '${body.promo}',
                      '${body.description_project}',
                      '${body.bedroom}',
                      '${body.bathroom}',
                      '${body.carport}',
                      '${body.building_area}',
                      '${body.surface_area}',
                      '${body.facility}',
                      '${body.name_developer}',
                      '${body.contact_developer}',
                      '${body.loan_bank}',
                      '${body.handover}')`;
  return dbpool.execute(SQLQuery);
}

const UpdateProject = (body, identifier_project) => {
  const SQLQuery = `UPDATE tb_projects 
                    SET 
                      name_project = '${body.name_project}',
                      type_project = '${body.type_project}',
                      access_project = '${body.access_project}',
                      status_project = '${body.status_project}',
                      location_project = '${body.location_project}',
                      price_list_project_cash = '${body.price_list_project_cash}',
                      price_list_project_credit = '${body.price_list_project_credit}',
                      promo = '${body.promo}',
                      description_project = '${body.description_project}',
                      bedroom = '${body.bedroom}',
                      bathroom = '${body.bathroom}',
                      carport = '${body.carport}',
                      building_area = '${body.building_area}',
                      surface_area = '${body.surface_area}',
                      facility = '${body.facility}',
                      name_developer = '${body.name_developer}',
                      contact_developer = '${body.contact_developer}',
                      loan_bank = '${body.loan_bank}',
                      handover = '${body.handover}'
                    WHERE identifier_project = '${identifier_project}'`;

  return dbpool.execute(SQLQuery);
};


const DeleteProject = (identifier_project) => {
  const SQLQuery = `DELETE FROM tb_projects WHERE identifier_project = '${identifier_project}'`;
  return dbpool.execute(SQLQuery);
}

module.exports = {
  getAllProject,
  createNewProject,
  UpdateProject,
  DeleteProject,
  getProjectbyIdentifier,
  checkProjectExistence,
  
}