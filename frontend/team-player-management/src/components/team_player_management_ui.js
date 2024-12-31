// Frontend design for Team and Player Management
// Utilizing React for UI and Axios for API communication

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Container, Row, Col, Form, Button, Table, Modal } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './styles.css'; // Include styles for background and other custom designs

// API URLs (update with actual backend endpoints)
const API_PLAYERS = 'http://localhost:8080/api/players';
const API_TEAMS = 'http://localhost:8080/api/teams';

// Team Management Page
export const TeamManagement = () => {
    const [teams, setTeams] = useState([]);
    const [selectedTeam, setSelectedTeam] = useState({ name: '', coach: '', city: '' });
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        fetchTeams();
    }, []);

    const fetchTeams = async () => {
        const response = await axios.get(API_TEAMS);
        setTeams(response.data);
    };

    const handleSave = async () => {
        if (selectedTeam.id) {
            await axios.put(`${API_TEAMS}/${selectedTeam.id}`, selectedTeam);
        } else {
            await axios.post(API_TEAMS, selectedTeam);
        }
        setShowModal(false);
        fetchTeams();
    };

    const handleDelete = async (id) => {
        await axios.delete(`${API_TEAMS}/${id}`);
        fetchTeams();
    };

    return (
        <Container className="team-management-page">
            <h1>Team Management</h1>
            <Button onClick={() => setShowModal(true)}>Add New Team</Button>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Coach</th>
                    <th>City</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {teams.map((team) => (
                    <tr key={team.id}>
                        <td>{team.name}</td>
                        <td>{team.coach}</td>
                        <td>{team.city}</td>
                        <td>
                            <Button
                                variant="warning"
                                onClick={() => {
                                    setSelectedTeam(team);
                                    setShowModal(true);
                                }}
                            >
                                Edit
                            </Button>
                            <Button
                                variant="danger"
                                onClick={() => handleDelete(team.id)}
                            >
                                Delete
                            </Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>

            <Modal show={showModal} onHide={() => setShowModal(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>{selectedTeam.id ? 'Edit Team' : 'Add Team'}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="teamName">
                            <Form.Label>Name</Form.Label>
                            <Form.Control
                                type="text"
                                value={selectedTeam.name}
                                onChange={(e) =>
                                    setSelectedTeam({ ...selectedTeam, name: e.target.value })
                                }
                            />
                        </Form.Group>
                        <Form.Group controlId="teamCoach">
                            <Form.Label>Coach</Form.Label>
                            <Form.Control
                                type="text"
                                value={selectedTeam.coach}
                                onChange={(e) =>
                                    setSelectedTeam({ ...selectedTeam, coach: e.target.value })
                                }
                            />
                        </Form.Group>
                        <Form.Group controlId="teamCity">
                            <Form.Label>City</Form.Label>
                            <Form.Control
                                type="text"
                                value={selectedTeam.city}
                                onChange={(e) =>
                                    setSelectedTeam({ ...selectedTeam, city: e.target.value })
                                }
                            />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShowModal(false)}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={handleSave}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </Container>
    );
};

// Player Management Page
export const PlayerManagement = () => {
    const [players, setPlayers] = useState([]);
    const [teams, setTeams] = useState([]);
    const [selectedPlayer, setSelectedPlayer] = useState({ name: '', position: '', teamId: '' });
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        fetchPlayers();
        fetchTeams();
    }, []);

    const fetchPlayers = async () => {
        const response = await axios.get(API_PLAYERS);
        setPlayers(response.data);
    };

    const fetchTeams = async () => {
        const response = await axios.get(API_TEAMS);
        setTeams(response.data);
    };

    const handleSave = async () => {
        if (selectedPlayer.id) {
            await axios.put(`${API_PLAYERS}/${selectedPlayer.id}`, selectedPlayer);
        } else {
            await axios.post(API_PLAYERS, selectedPlayer);
        }
        setShowModal(false);
        fetchPlayers();
    };

    const handleDelete = async (id) => {
        await axios.delete(`${API_PLAYERS}/${id}`);
        fetchPlayers();
    };

    return (
        <Container className="player-management-page">
            <h1>Player Management</h1>
            <Button onClick={() => setShowModal(true)}>Add New Player</Button>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Position</th>
                    <th>Team</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {players.map((player) => (
                    <tr key={player.id}>
                        <td>{player.name}</td>
                        <td>{player.position}</td>
                        <td>{player.team?.name || 'Unassigned'}</td>
                        <td>
                            <Button
                                variant="warning"
                                onClick={() => {
                                    setSelectedPlayer(player);
                                    setShowModal(true);
                                }}
                            >
                                Edit
                            </Button>
                            <Button
                                variant="danger"
                                onClick={() => handleDelete(player.id)}
                            >
                                Delete
                            </Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>

            <Modal show={showModal} onHide={() => setShowModal(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>{selectedPlayer.id ? 'Edit Player' : 'Add Player'}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="playerName">
                            <Form.Label>Name</Form.Label>
                            <Form.Control
                                type="text"
                                value={selectedPlayer.name}
                                onChange={(e) =>
                                    setSelectedPlayer({ ...selectedPlayer, name: e.target.value })
                                }
                            />
                        </Form.Group>
                        <Form.Group controlId="playerPosition">
                            <Form.Label>Position</Form.Label>
                            <Form.Control
                                type="text"
                                value={selectedPlayer.position}
                                onChange={(e) =>
                                    setSelectedPlayer({ ...selectedPlayer, position: e.target.value })
                                }
                            />
                        </Form.Group>
                        <Form.Group controlId="playerTeam">
                            <Form.Label>Team</Form.Label>
                            <Form.Control
                                as="select"
                                value={selectedPlayer.teamId}
                                onChange={(e) =>
                                    setSelectedPlayer({ ...selectedPlayer, teamId: e.target.value })
                                }
                            >
                                <option value="">Select a team</option>
                                {teams.map((team) => (
                                    <option key={team.id} value={team.id}>
                                        {team.name}
                                    </option>
                                ))}
                            </Form.Control>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShowModal(false)}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={handleSave}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </Container>
    );
};
