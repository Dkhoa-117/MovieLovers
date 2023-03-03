import express from "express";
const router = express.Router();
import comments from "../controllers/comments.js";

router.route("/all").get(comments.getAll);

export default router;
