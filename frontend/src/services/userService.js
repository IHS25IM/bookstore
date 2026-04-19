import { myAxios } from "../helper";

export const signup = (customer) => {
  return myAxios.post("/api/customers/register", customer).then((response) => response.data);
};
