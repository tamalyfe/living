const dbpool = require('../config/database')

const getAllRecruitment = () => {
  const SQLQuery = 'SELECT * FROM tb_recruitment_teams'

  return dbpool.execute(SQLQuery);
}

const getRecruitmentbyIdentifier = (identifier_recruitment_team) => {
  const SQLQuery = `SELECT * FROM tb_recruitment_teams WHERE identifier_recruitment_team = '${identifier_recruitment_team}'`;

  return dbpool.execute(SQLQuery);
}

const checkRecruitmentIdentifier = (body) => {
  const SQLQuery = `
      SELECT identifier_recruitment_team
      FROM tb_recruitment_teams
      WHERE
          name_team = '${body.name_team}' AND
          post_team = '${body.post_team}' AND
          domicile_team = '${body.domicile_team}' AND
          job_description = '${body.job_description}' AND
          experience = '${body.experience}' AND
          certificate = '${body.certificate}';
  `;

  return dbpool.execute(SQLQuery)
      .then(([result]) => ({ identifier_recruitment_team: result.length > 0 ? result[0].identifier_recruitment_team : null }))
      .catch((error) => {
          throw error;
      });
};


const createNewRecruitment = (body) => {
  const SQLQuery = `INSERT INTO tb_recruitment_teams 
                    (identifier_recruitment_team, name_team, post_team, domicile_team, job_description, experience, certificate) 
                    VALUES (
                      '${body.identifier_recruitment_team}',
                      '${body.name_team}',
                      '${body.post_team}',
                      '${body.domicile_team}',
                      '${body.job_description}',
                      '${body.experience}',
                      '${body.certificate}'
                    )`;

  return dbpool.execute(SQLQuery);
}

const createNewRecruitmentLoginData = (body) =>{
  const SQLQuery = `INSERT INTO tb_recruitment_team_login_data 
                  (identifier_recruitment_team, 
                    email_recruitment_team, 
                    password_recruitment_team, 
                    username)
                  VALUES (${body.identifier_recruitment_team}, 
                    '${body.email_recruitment_team}', 
                    '${body.password_recruitment_team}', 
                    '${body.username}') `;
  return dbpool.execute(SQLQuery);
}

const UpdateRecruitment = (body, identifier_recruitment_team) => {
  const SQLQuery = `UPDATE tb_recruitment_teams 
                  SET 
                    name_team = '${body.name_team}',
                    post_team = '${body.post_team}',
                    domicile_team = '${body.domicile_team}',
                    job_description = '${body.job_description}',
                    experience = '${body.experience}',
                    certificate = '${body.certificate}'
                  WHERE identifier_recruitment_team = '${identifier_recruitment_team}'`;

  return dbpool.execute(SQLQuery);

}

const DeleteRecruitment = (identifier_recruitment_team) => {
  const SQLQuery = `DELETE FROM tb_recruitment_teams identifier_recruitment_team = '${identifier_recruitment_team}'`;
  return dbpool.execute(SQLQuery);
}

const checkDataRecruitmentLogin = ({ email, username }) => {
  const condition = email ? `email_recruitment_team = ?` : `username = ?`;

  const SQLQuery = `
      SELECT
          username,
          email_recruitment_team AS email,
          password_recruitment_team AS password,
          -- Add other columns you want to retrieve
          CASE
              WHEN COUNT(*) > 0 THEN 1
              ELSE 0
          END AS user_exists
      FROM tb_recruitment_team_login_data
      WHERE
          ${condition};
  `;

  return dbpool.execute(SQLQuery, [email || username]);
};

const checkUsernameExists = (username) => {
  const SQLQuery = `
      SELECT COUNT(*) AS usernameCount
      FROM tb_recruitment_team_login_data
      WHERE username = ?;
  `;

  return dbpool.execute(SQLQuery, [username])
      .then(([result]) => result[0].usernameCount > 0 ? 1 : 0)
      .catch((error) => {
          console.error(error);
          return 0;
      });
};


module.exports = {
  getAllRecruitment,
  createNewRecruitment,
  UpdateRecruitment,
  DeleteRecruitment,
  getRecruitmentbyIdentifier,
  checkRecruitmentIdentifier,
  checkDataRecruitmentLogin,
  createNewRecruitmentLoginData,
  checkUsernameExists
}