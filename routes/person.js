import express from "express";
const router = express.Router();

import person from "../controllers/person.js";

router.route("/all").get(person.getAll);

export default router;
