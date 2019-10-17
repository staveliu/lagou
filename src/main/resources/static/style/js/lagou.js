// api url
const HOST_URL = "http://localhost:8086/"

const TOKEN_KEY = "jwtToken"

function getJwtToken() {
  return localStorage.getItem(TOKEN_KEY);
}

function setJwtToken(token) {
  localStorage.setItem(TOKEN_KEY, token);
}

function removeJwtToken() {
  localStorage.removeItem(TOKEN_KEY);
}

function createAuthorizationTokenHeader() {
  var token = getJwtToken();
  // console.log(token)
  if (token) {
    return {"Authorization": "Bearer " + token};
  } else {
    return {};
  }
}

/**
 * ���ٵĽ� form ������תΪ json ��ʽ
 * @param $form
 */
function getFormData($form) {
  var unindexed_array = $form.serializeArray()
  var indexed_array = {}

  $.map(unindexed_array, (n, i) => {
    indexed_array[n['name']] = n['value']
  })

  return indexed_array
}