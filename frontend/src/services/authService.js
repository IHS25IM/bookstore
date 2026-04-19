import { myAxios } from "../helper";

export const register = (data) =>
  myAxios.post("/api/auth/register", data).then((r) => r.data);

export const login = (data) =>
  myAxios.post("/api/auth/login", data).then((r) => r.data);